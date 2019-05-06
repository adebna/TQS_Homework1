package lei.tqs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HomeworkApplication.class)
@AutoConfigureMockMvc
public class WeatherInfoFromLocationControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @Test
    public void testWeatherInfoCoordinatesAveiro() throws Exception  {
        mvc.perform(MockMvcRequestBuilders.get("/weather/coordinates?lat=40.640496&lon=-8.6537841&begin_timestamp=1556970577&end_timestamp=1556970577").
        accept(MediaType.APPLICATION_JSON)).
        andExpect(status().isOk()).
        andExpect(MockMvcResultMatchers.jsonPath("$.locationName").exists()).
        andExpect(MockMvcResultMatchers.jsonPath("$.locationName").value("Aveiro")).
        andExpect(MockMvcResultMatchers.jsonPath("$.weekWeatherData").isArray()).
        andExpect(MockMvcResultMatchers.jsonPath("$.weekWeatherData").isNotEmpty());
    }
    
    @Test
    public void testWeatherInfoCoordinatesFeshi() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/weather/coordinates?lat=-6.1822&lon=18.1714&begin_timestamp=1556970577&end_timestamp=1556970577").
        accept(MediaType.APPLICATION_JSON)).
        andExpect(status().isOk()).
        andExpect(MockMvcResultMatchers.jsonPath("$.locationName").exists()).
        andExpect(MockMvcResultMatchers.jsonPath("$.locationName").value("Feshi")).
        andExpect(MockMvcResultMatchers.jsonPath("$.weekWeatherData").isArray()).
        andExpect(MockMvcResultMatchers.jsonPath("$.weekWeatherData").isNotEmpty());
    }
    
    @Test
    public void testWeatherInfoCoordinatesAngola() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/weather/coordinates?lat=-13.2593&lon=21.2256&begin_timestamp=1556970577&end_timestamp=1556970577").
        accept(MediaType.APPLICATION_JSON)).
        andExpect(status().isOk()).
        andExpect(MockMvcResultMatchers.jsonPath("$.locationName").exists()).
        andExpect(MockMvcResultMatchers.jsonPath("$.locationName").value("Angola")).
        andExpect(MockMvcResultMatchers.jsonPath("$.weekWeatherData").isArray()).
        andExpect(MockMvcResultMatchers.jsonPath("$.weekWeatherData").isNotEmpty());
    }
    
    @Test
    public void testWeatherInfoCoordinatesWithInvalidCoordinates1() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/weather/coordinates?lat=180&lon=180&begin_timestamp=1556970577&end_timestamp=1556970577").
        accept(MediaType.APPLICATION_JSON)).
        andExpect(status().isOk()).
        andExpect(MockMvcResultMatchers.jsonPath("$.locationName").exists()).
        andExpect(MockMvcResultMatchers.jsonPath("$.locationName").value("")).
        andExpect(MockMvcResultMatchers.jsonPath("$.weekWeatherData").exists()).
        andExpect(MockMvcResultMatchers.jsonPath("$.weekWeatherData").isEmpty());
    }
    
    @Test
    public void testWeatherInfoCoordinatesWithInvalidCoordinates2() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/weather/coordinates?lat=-180&lon=-180&begin_timestamp=1556970577&end_timestamp=1556970577").
        accept(MediaType.APPLICATION_JSON)).
        andExpect(status().isOk()).
        andExpect(MockMvcResultMatchers.jsonPath("$.locationName").exists()).
        andExpect(MockMvcResultMatchers.jsonPath("$.locationName").value("")).
        andExpect(MockMvcResultMatchers.jsonPath("$.weekWeatherData").exists()).
        andExpect(MockMvcResultMatchers.jsonPath("$.weekWeatherData").isEmpty());
    }
    
    @Test
    public void testWeatherInfoLocationAveiro() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/weather/city?city=Aveiro&begin_timestamp=1556970577&end_timestamp=1556970577").
        accept(MediaType.APPLICATION_JSON)).
        andExpect(status().isOk()).
        andExpect(MockMvcResultMatchers.jsonPath("$.latitude").exists()).
        andExpect(MockMvcResultMatchers.jsonPath("$.latitude").value(40.640496)).
        andExpect(MockMvcResultMatchers.jsonPath("$.longitude").exists()).
        andExpect(MockMvcResultMatchers.jsonPath("$.longitude").value(-8.6537841)).
        andExpect(MockMvcResultMatchers.jsonPath("$.weekWeatherData").isArray()).
        andExpect(MockMvcResultMatchers.jsonPath("$.weekWeatherData").isNotEmpty());
    }
    
    @Test
    public void testWeatherInfoLocationCoimbra() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/weather/city?city=Coimbra&begin_timestamp=1556970577&end_timestamp=1556970577").
        accept(MediaType.APPLICATION_JSON)).
        andExpect(status().isOk()).
        andExpect(MockMvcResultMatchers.jsonPath("$.latitude").exists()).
        andExpect(MockMvcResultMatchers.jsonPath("$.latitude").value(40.2109801)).
        andExpect(MockMvcResultMatchers.jsonPath("$.longitude").exists()).
        andExpect(MockMvcResultMatchers.jsonPath("$.longitude").value(-8.4292057)).
        andExpect(MockMvcResultMatchers.jsonPath("$.weekWeatherData").isArray()).
        andExpect(MockMvcResultMatchers.jsonPath("$.weekWeatherData").isNotEmpty());
    }
}