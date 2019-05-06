package lei.tqs.cache;

import lei.tqs.domain.WeatherInfoFromLocation;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class WeatherRepositoryTest {
    
    private static WeatherRepository weatherRepository;
    
    public WeatherRepositoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        weatherRepository = new WeatherRepository();
    }
    
    @Before
    public void setUp() {
        weatherRepository = new WeatherRepository();
    }
    
    /**
     * Test of insert method, of class WeatherRepository.
     */
    @Test
    public void testInsertHasKeyAndRetrieve() {
        weatherRepository.insert("teste", new WeatherInfoFromLocation(0, 0, "teste", null));
        assert(weatherRepository.hasKey("teste"));
        assertEquals(0.0, weatherRepository.retrieve("teste").getLatitude(), 0.0);
    }
    
    @Test
    public void testLookupAfterTTL() {
        WeatherRepository.ttl = 1;
        weatherRepository.insert("teste", new WeatherInfoFromLocation(0, 0, "teste", null));
        for (long i = 0; i < 99999; i++) {
            for (long j = 0; j < 223456; j++) {
                Math.sqrt(999999);
            }
        }
        assertEquals(null, weatherRepository.retrieve("teste"));
    }
    
    @Test
    public void testDeleteCache() {
        weatherRepository.insert("teste", new WeatherInfoFromLocation(0, 0, "teste", null));
        weatherRepository.deleteCache();
        assertEquals(null, weatherRepository.retrieve("teste"));
    }
    
    @Test
    public void testStatistics() {
        weatherRepository.insert("teste1", new WeatherInfoFromLocation(0, 0, "teste1", null));
        weatherRepository.insert("teste2", new WeatherInfoFromLocation(0, 0, "teste2", null));
        if (weatherRepository.hasKey("teste1"))
            weatherRepository.retrieve("teste1");
        if (weatherRepository.hasKey("teste3"))
            weatherRepository.retrieve("teste3");
        assertEquals(50.0, weatherRepository.getHitPercentage(), 0.0);
        assertEquals(50.0, weatherRepository.getMissPercentage(), 0.0);
        assertEquals(2, weatherRepository.getNumberOfRequests());
    }
}
