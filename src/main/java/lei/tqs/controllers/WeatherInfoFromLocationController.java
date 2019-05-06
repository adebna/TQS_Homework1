package lei.tqs.controllers;

import lei.tqs.domain.WeatherInfoFromLocation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.text.WordUtils;
import java.util.List;
import lei.tqs.cache.WeatherRepository;
import lei.tqs.domain.DataWeatherBlock;
import lei.tqs.services.DarkSkyUtil;
import lei.tqs.services.NominatimUtil;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/weather")
public class WeatherInfoFromLocationController {
    
    private NominatimUtil nominatimUtil = new NominatimUtil();
    private DarkSkyUtil darkSkyUtil = new DarkSkyUtil();
    private WeatherRepository cache = new WeatherRepository();
    
    @GetMapping(path = "/coordinates")
    public WeatherInfoFromLocation weatherInfo(@RequestParam(value="lat") double lat, 
                                               @RequestParam(value="lon") double lon,
                                               @RequestParam(value="begin_timestamp") long beginTimestamp,
                                               @RequestParam(value="end_timestamp") long endTimestamp){
        String location = nominatimUtil.getLocationFromCoordinates(lat, lon);
        if (cache.hasKey(location))
            return cache.retrieve(location);
        List<DataWeatherBlock> weekWeatherData = darkSkyUtil.getWeatherFromCoordinates(lat, lon, beginTimestamp, endTimestamp);
        WeatherInfoFromLocation result = new WeatherInfoFromLocation(lat, lon, location, weekWeatherData);
        cache.insert(location, result);
        return result;
    }
    
    @GetMapping(path = "/city")
    public WeatherInfoFromLocation weatherInfo(@RequestParam(value="city") String location,
                                               @RequestParam(value="begin_timestamp") long beginTimestamp,
                                               @RequestParam(value="end_timestamp") long endTimestamp) {
        if (cache.hasKey(location))
            return cache.retrieve(location);
        location = WordUtils.capitalizeFully(location);
        Double[] coordinates = nominatimUtil.getCoordinatesFromLocation(location);
        Double lat = coordinates[0];
        Double lon = coordinates[1];
        List<DataWeatherBlock> weekWeatherData = darkSkyUtil.getWeatherFromCoordinates(lat, lon, beginTimestamp, endTimestamp);
        WeatherInfoFromLocation result = new WeatherInfoFromLocation(lat, lon, location, weekWeatherData);
        cache.insert(location, result);
        return result;
    }
}