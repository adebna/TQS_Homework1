package lei.tqs.services;

import java.util.Arrays;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class NominatimUtil {
    
    RestTemplate restTemplate = new RestTemplate();
    static String locationUrl = "https://nominatim.openstreetmap.org/";//reverse?format=json&",
    static HttpHeaders headers = new HttpHeaders();
    static HttpEntity<String> entity;
    static {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        entity = new HttpEntity<>("parameters", headers);
    }
    
    public String getLocationFromCoordinates(double lat, double lon) {
        if (lat < -90 || lat > 90 || lon < -180 || lon > 180)
            return "";
        JSONObject locationJson;
        try {
            locationJson = new JSONObject(restTemplate.exchange(
                                           NominatimUtil.locationUrl+"reverse?format=json&lat="+lat+"&lon="+lon,
                                           HttpMethod.GET,
                                           entity,
                                           String.class
                                       ).getBody()).getJSONObject("address");
        } catch (RestClientException e) {
            return "";
        }
        if (locationJson.has("town"))
            return locationJson.getString("town");
        if (locationJson.has("county")) {
            String county = locationJson.getString("county");
            if (!county.toUpperCase().equals(county))
                return county;
        }
        if (locationJson.has("state_district"))
            return locationJson.getString("state_district");
        return locationJson.getString("country");
    }
    
    public Double[] getCoordinatesFromLocation(String location) {
        JSONObject locationJson;
        try {
            locationJson =  new JSONObject("{" + restTemplate.exchange(
                                            locationUrl+"search.php?format=json&city="+location,
                                            HttpMethod.GET,
                                            entity,
                                            String.class
                                        ).getBody().split("[{}]")[1] + "}");
        } catch (RestClientException e) {
            return new Double[] {-999.0, -999.0};
        }
        Double lat = Double.parseDouble(locationJson.getString("lat"));
        Double lon = Double.parseDouble(locationJson.getString("lon"));
        return new Double[] {lat, lon};
    }

}
