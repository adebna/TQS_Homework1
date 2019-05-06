package lei.tqs.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lei.tqs.domain.WeatherInfoFromLocation;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherRepository {
    
    static int ttl = 8;
    private int hits = 0;
    private int misses = 0;
    private Map<String, WeatherInfoFromLocation> cache = new HashMap<>();
    
    public void insert(String url, WeatherInfoFromLocation result) {
        this.cache.put(url, result);
        setTimeout(() -> cleanCache(url), ttl*1000);
    }
    
    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (InterruptedException ex){
                Logger.getLogger(WeatherRepository.class.getName()).log(Level.SEVERE, null, ex);
                Thread.currentThread().interrupt();
            }
        }).start();
    }
    
    public boolean hasKey(String location) {
        if (this.cache.containsKey(location))
            return true;
        this.misses++;
        return false;
    }
    
    public WeatherInfoFromLocation retrieve(String location) {
        this.hits++;
        return this.cache.getOrDefault(location, null);
    }
    
    public void deleteCache() {
        this.cache = new HashMap<>();
    }
    
    public void cleanCache(String location) {
        this.cache.remove(location);
    }
    
    public double getHitPercentage() {
        return ((double)this.hits/this.getNumberOfRequests()) * 100;
    }
    
    public double getMissPercentage() {
        return ((double)this.misses/this.getNumberOfRequests()) * 100;
    }
    
    public int getNumberOfRequests() {
        return this.hits+this.misses;
    }
    
}
