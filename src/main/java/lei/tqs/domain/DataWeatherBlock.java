package lei.tqs.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public class DataWeatherBlock {
    
    private long time;
    private String summary;
    private String icon;
    private double precipIntensity;
    private double precipProbability;
    private String precipType;
    private double temperatureMax;
    private double temperatureMin;
    private double humidity;
    private double windSpeed;
    private double cloudCover;

    @JsonCreator
    public DataWeatherBlock(long time, 
                            String summary, 
                            String icon, 
                            double precipIntensity, 
                            double precipProbability, 
                            String precipType, 
                            double temperatureMax, 
                            double temperatureMin, 
                            double humidity, 
                            double windSpeed, 
                            double cloudCover) {
        this.time = time;
        this.summary = summary;
        this.icon = icon;
        this.precipIntensity = precipIntensity;
        this.precipProbability = precipProbability;
        this.precipType = precipType;
        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.cloudCover = cloudCover;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(double precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public double getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(double precipProbability) {
        this.precipProbability = precipProbability;
    }

    public String getPrecipType() {
        return precipType;
    }

    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(double cloudCover) {
        this.cloudCover = cloudCover;
    }
}