package lei.tqs.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.List;

/**
 * API URL: https://api.darksky.net/forecast/a1515bd2809b237cb6be17038aa11c92/41.1455,%20-8.5261?exclude=minutely,hourly,flags&lang=pt&units=si
 * https://nominatim.openstreetmap.org/reverse?format=json&lat=41.1455&lon=-8.5261&zoom=8
 * 
 */
public class WeatherInfoFromLocation {
    
    private double latitude;
    private double longitude;
    private String locationName;
    List<DataWeatherBlock> weekWeatherData;
    
    @JsonCreator
    public WeatherInfoFromLocation(double latitude, double longitude, String locationName, List<DataWeatherBlock> weekWeatherData) {
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.weekWeatherData = weekWeatherData;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<DataWeatherBlock> getWeekWeatherData() {
        return weekWeatherData;
    }

    public void setWeekWeatherData(List<DataWeatherBlock> weekWeatherData) {
        this.weekWeatherData = weekWeatherData;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
