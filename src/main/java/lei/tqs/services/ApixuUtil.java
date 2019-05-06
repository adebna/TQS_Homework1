package lei.tqs.services;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lei.tqs.domain.DataWeatherBlock;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ApixuUtil {
    
    static String baseURL = "http://api.apixu.com/v1/history.json?key=6fe319400df94011b30135210190405";//&q=40.640496,-8.6537842&dt=2019-04-30"
    RestTemplate restTemplate = new RestTemplate();
    static Gson gson = new Gson();
    
    public List<DataWeatherBlock> getWeatherFromCoordinates(double lat, double lon, long beginTimestamp, long endTimestamp) {
        List<DataWeatherBlock> weekWeatherData = new ArrayList<>();
        for (long dayTimestamp = beginTimestamp; dayTimestamp <= endTimestamp; dayTimestamp += 86400) {
            String date = (new Date(dayTimestamp*1000)).toInstant().toString();
            String weatherUrl = ApixuUtil.baseURL+"&q="+lat+","+lon+"&dt="+date.substring(0, 10);
            JSONObject forecast;
            try {
                forecast = new JSONObject(restTemplate.getForObject(weatherUrl,String.class));
            } catch (RestClientException e) {
                continue;
            }
            forecast = forecast.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0).getJSONObject("day");
            JSONObject condition = forecast.getJSONObject("condition"); 
            Double perc = forecast.getDouble("totalprecip_mm");
            DataWeatherBlock dataWeatherBlock = new DataWeatherBlock(dayTimestamp, 
                    condition.getString("text"), 
                    "http:"+condition.getString("icon"),
                    perc, 
                    perc, 
                    condition.getString("text"), 
                    forecast.getDouble("maxtemp_c"), 
                    forecast.getDouble("mintemp_c"), 
                    perc, 
                    forecast.getDouble("maxwind_kph"), 
                    forecast.getDouble("totalprecip_mm"));
            weekWeatherData.add(dataWeatherBlock);
        }
        return weekWeatherData;
    }
}