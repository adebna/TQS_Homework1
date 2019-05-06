package lei.tqs.services;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lei.tqs.domain.DataWeatherBlock;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class DarkSkyUtil {
    
    static String baseURL = "https://api.darksky.net/forecast/3f005ad92ae8f8e6cb75fca762b09e87/";
    RestTemplate restTemplate = new RestTemplate();
    static Gson gson = new Gson();
    ApixuUtil backupAPI = new ApixuUtil();
    
    public List<DataWeatherBlock> getWeatherFromCoordinates(double lat, double lon, long beginTimestamp, long endTimestamp) {
        if (lat < -90 || lat > 90 || lon < -180 || lon > 180)
            return new LinkedList<>();
        List<DataWeatherBlock> weekWeatherData = new ArrayList<>();
        for (long dayTimestamp = beginTimestamp; dayTimestamp <= endTimestamp; dayTimestamp += 86400) {
            String weatherUrl = DarkSkyUtil.baseURL+lat+","+lon+","+dayTimestamp+
                                 "?exclude=minutely,hourly,flags&lang=pt&units=si";
            try {
                JSONObject forecast = new JSONObject(restTemplate.getForObject(weatherUrl,String.class));
                if (!forecast.has("daily")) {
                   weekWeatherData.addAll(backupAPI.getWeatherFromCoordinates(lat, lon, dayTimestamp, dayTimestamp));
                   continue;
                }
                for (String json : forecast.getJSONObject("daily").
                     getJSONArray("data").toString().split("[{}]"))
                    if (!json.equals("[") && !json.equals("]"))
                        weekWeatherData.add(gson.fromJson("{"+json+"}", DataWeatherBlock.class));
            } catch (RestClientException e) {
                weekWeatherData.addAll(backupAPI.getWeatherFromCoordinates(lat, lon, dayTimestamp, dayTimestamp));
            }
        }
        return weekWeatherData;
    }
}