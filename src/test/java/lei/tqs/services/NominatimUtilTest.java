package lei.tqs.services;

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
public class NominatimUtilTest {
    
    @TestConfiguration
    static class NominatimUtilTestContextConfig {
        @Bean
        public NominatimUtil nominatimUtils() {return new NominatimUtil();}
    }
    
    @Autowired
    private NominatimUtil nominatimUtil;
    
    @MockBean
    private RestTemplate restTemplate;
    static HttpHeaders headers = new HttpHeaders();
    static HttpEntity<String> entity;
    static {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        entity = new HttpEntity<>("parameters", headers);
    }
    
    @Before
    public void setUp() {
        nominatimUtil.restTemplate = restTemplate;
        Mockito.when(
            restTemplate.exchange(
               NominatimUtil.locationUrl+"reverse?format=json&lat=40.640496&lon=-8.6537841",
               HttpMethod.GET,
               entity,
               String.class
            )
        ).thenReturn(new ResponseEntity<>("{\"place_id\":198961559,\"licence\":\"Data © OpenStreetMap contributors, ODbL 1.0. https://osm.org/copyright\",\"osm_type\":\"relation\",\"osm_id\":3920249,\"lat\":\"40.67915045\",\"lon\":\"-8.52171661522701\",\"display_name\":\"Aveiro, Baixo Vouga, Centro, Portugal\",\"address\":{\"county\":\"PRT\",\"state_district\":\"Baixo Vouga\",\"state\":\"Centro\",\"country\":\"Portugal\",\"country_code\":\"pt\"},\"boundingbox\":[\"40.2788096\",\"41.0801546\",\"-8.7840289\",\"-8.0891722\"]}", null, HttpStatus.CREATED));
        Mockito.when(
            restTemplate.exchange(
               NominatimUtil.locationUrl+"reverse?format=json&lat=40.0&lon=-8.0",
               HttpMethod.GET,
               entity,
               String.class
            )
        ).thenReturn(new ResponseEntity<>("{\"place_id\":198961559,\"licence\":\"Data © OpenStreetMap contributors, ODbL 1.0. https://osm.org/copyright\",\"osm_type\":\"relation\",\"osm_id\":3920249,\"lat\":\"40.67915045\",\"lon\":\"-8.52171661522701\",\"display_name\":\"Aveiro, Baixo Vouga, Centro, Portugal\",\"address\":{\"county\":\"PRT\",\"state\":\"Centro\",\"country\":\"Portugal\",\"country_code\":\"pt\"},\"boundingbox\":[\"40.2788096\",\"41.0801546\",\"-8.7840289\",\"-8.0891722\"]}", null, HttpStatus.CREATED));
        Mockito.when(
            restTemplate.exchange(
               NominatimUtil.locationUrl+"search.php?format=json&city=Aveiro",
               HttpMethod.GET,
               entity,
               String.class
            )
        ).thenReturn(new ResponseEntity<>("[{\"place_id\":38463403,\"licence\":\"Data © OpenStreetMap contributors, ODbL 1.0. https://osm.org/copyright\",\"osm_type\":\"node\",\"osm_id\":2912652994,\"boundingbox\":[\"40.480496\",\"40.800496\",\"-8.8137841\",\"-8.4937841\"],\"lat\":\"40.640496\",\"lon\":\"-8.6537841\",\"display_name\":\"Aveiro, Baixo Vouga, Centro, 3810-086, Portugal\",\"class\":\"place\",\"type\":\"city\",\"importance\":0.591436404888537,\"icon\":\"https://nominatim.openstreetmap.org/images/mapicons/poi_place_city.p.20.png\"},{\"place_id\":198990350,\"licence\":\"Data © OpenStreetMap contributors, ODbL 1.0. https://osm.org/copyright\",\"osm_type\":\"relation\",\"osm_id\":5325138,\"boundingbox\":[\"40.5284837\",\"40.7275536\",\"-8.7655529\",\"-8.5209649\"],\"lat\":\"40.6275896\",\"lon\":\"-8.60742264933243\",\"display_name\":\"Aveiro, Baixo Vouga, Centro, Portugal\",\"class\":\"boundary\",\"type\":\"administrative\",\"importance\":0.591436404888537,\"icon\":\"https://nominatim.openstreetmap.org/images/mapicons/poi_boundary_administrative.p.20.png\"},{\"place_id\":198961559,\"licence\":\"Data © OpenStreetMap contributors, ODbL 1.0. https://osm.org/copyright\",\"osm_type\":\"relation\",\"osm_id\":3920249,\"boundingbox\":[\"40.2788096\",\"41.0801546\",\"-8.7840289\",\"-8.0891722\"],\"lat\":\"40.67915045\",\"lon\":\"-8.52171661522701\",\"display_name\":\"Aveiro, Baixo Vouga, Centro, Portugal\",\"class\":\"boundary\",\"type\":\"administrative\",\"importance\":0.510826321041697,\"icon\":\"https://nominatim.openstreetmap.org/images/mapicons/poi_boundary_administrative.p.20.png\"},{\"place_id\":199370378,\"licence\":\"Data © OpenStreetMap contributors, ODbL 1.0. https://osm.org/copyright\",\"osm_type\":\"relation\",\"osm_id\":6275219,\"boundingbox\":[\"-4.1542282\",\"-3.23236\",\"-55.6052354\",\"-55.0373984\"],\"lat\":\"-3.69233475\",\"lon\":\"-55.2845763443219\",\"display_name\":\"Aveiro, Microrregião de Itaituba, Mesorregião Sudoeste Paraense, Pará, North Region, Brazil\",\"class\":\"boundary\",\"type\":\"administrative\",\"importance\":0.4,\"icon\":\"https://nominatim.openstreetmap.org/images/mapicons/poi_boundary_administrative.p.20.png\"},{\"place_id\":17370691,\"licence\":\"Data © OpenStreetMap contributors, ODbL 1.0. https://osm.org/copyright\",\"osm_type\":\"node\",\"osm_id\":1650269000,\"boundingbox\":[\"40.6432909\",\"40.6433909\",\"-8.6404431\",\"-8.6403431\"],\"lat\":\"40.6433409\",\"lon\":\"-8.6403931\",\"display_name\":\"Aveiro, Avenida Doutor Lourenço Peixinho, Vera Cruz, Esgueira, Aveiro, Baixo Vouga, Centro, 3800-099, Portugal\",\"class\":\"railway\",\"type\":\"stop\",\"importance\":0.379165948943713},{\"place_id\":17337228,\"licence\":\"Data © OpenStreetMap contributors, ODbL 1.0. https://osm.org/copyright\",\"osm_type\":\"node\",\"osm_id\":1650269019,\"boundingbox\":[\"40.6433727\",\"40.6434727\",\"-8.6408335\",\"-8.6407335\"],\"lat\":\"40.6434227\",\"lon\":\"-8.6407835\",\"display_name\":\"Aveiro, Rua Doutor Arlindo Vicente, Vera Cruz, Glória e Vera Cruz, Aveiro, Baixo Vouga, Centro, 3800260, Portugal\",\"class\":\"railway\",\"type\":\"stop\",\"importance\":0.379165948943713},{\"place_id\":198202298,\"licence\":\"Data © OpenStreetMap contributors, ODbL 1.0. https://osm.org/copyright\",\"osm_type\":\"relation\",\"osm_id\":185570,\"boundingbox\":[\"-4.3051198\",\"-3.2211765\",\"-56.9560834\",\"-55.0373984\"],\"lat\":\"-3.6049239\",\"lon\":\"-55.3310742\",\"display_name\":\"Aveiro, Microrregião de Itaituba, Mesorregião Sudoeste Paraense, Pará, North Region, Brazil\",\"class\":\"boundary\",\"type\":\"administrative\",\"importance\":0.318700452394596,\"icon\":\"https://nominatim.openstreetmap.org/images/mapicons/poi_boundary_administrative.p.20.png\"},{\"place_id\":13641084,\"licence\":\"Data © OpenStreetMap contributors, ODbL 1.0. https://osm.org/copyright\",\"osm_type\":\"node\",\"osm_id\":1242490108,\"boundingbox\":[\"42.1113785\",\"42.1313785\",\"-8.5490654\",\"-8.5290654\"],\"lat\":\"42.1213785\",\"lon\":\"-8.5390654\",\"display_name\":\"Aveiró, Salceda de Caselas, Pontevedra, Galicia, 36863, Spain\",\"class\":\"place\",\"type\":\"locality\",\"importance\":0.25,\"icon\":\"https://nominatim.openstreetmap.org/images/mapicons/poi_place_village.p.20.png\"},{\"place_id\":18603351,\"licence\":\"Data © OpenStreetMap contributors, ODbL 1.0. https://osm.org/copyright\",\"osm_type\":\"node\",\"osm_id\":1770524354,\"boundingbox\":[\"-23.6408391\",\"-23.6407391\",\"-46.6673046\",\"-46.6672046\"],\"lat\":\"-23.6407891\",\"lon\":\"-46.6672546\",\"display_name\":\"Aveiro, Avenida Santa Catarina, Campo Belo, São Paulo, Região Imediata de São Paulo, RMSP, Região Intermediária de São Paulo, São Paulo, Southeast Region, 04626001, Brazil\",\"class\":\"shop\",\"type\":\"bakery\",\"importance\":0.101,\"icon\":\"https://nominatim.openstreetmap.org/images/mapicons/shopping_bakery.p.20.png\"},{\"place_id\":56538641,\"licence\":\"Data © OpenStreetMap contributors, ODbL 1.0. https://osm.org/copyright\",\"osm_type\":\"node\",\"osm_id\":4549160276,\"boundingbox\":[\"-22.8915803\",\"-22.8914803\",\"-47.0554769\",\"-47.0553769\"],\"lat\":\"-22.8915303\",\"lon\":\"-47.0554269\",\"display_name\":\"Aveiro, 374, Rua Doutor Vieira Bueno, Cambuí, Campinas, Região Imediata de Campinas, RMC, Região Intermediária de Campinas, São Paulo, Southeast Region, 13025-152, Brazil\",\"class\":\"office\",\"type\":\"administrative\",\"importance\":0.101}]", null, HttpStatus.CREATED));
        Mockito.when(
            restTemplate.exchange(
               NominatimUtil.locationUrl+"search.php?format=json&city=TESTE",
               HttpMethod.GET,
               entity,
               String.class
            )
        ).thenThrow(new RestClientException(""));
        Mockito.when(
            restTemplate.exchange(
               NominatimUtil.locationUrl+"reverse?format=json&lat=90&lon=181",
               HttpMethod.GET,
               entity,
               String.class
            )
        ).thenReturn(new ResponseEntity<>("", null, HttpStatus.CREATED));
        Mockito.when(
            restTemplate.exchange(
               NominatimUtil.locationUrl+"reverse?format=json&lat=-90&lon=-181",
               HttpMethod.GET,
               entity,
               String.class
            )
        ).thenReturn(new ResponseEntity<>("", null, HttpStatus.CREATED));
        Mockito.when(
            restTemplate.exchange(
               NominatimUtil.locationUrl+"reverse?format=json&lat=0.0&lon=0.0",
               HttpMethod.GET,
               entity,
               String.class
            )
        ).thenThrow(new RestClientException(""));
    }
    
    @Test
    public void testGetLocationFromCoordinates1() {
        assertEquals("Baixo Vouga", nominatimUtil.getLocationFromCoordinates(40.640496, -8.6537841));
    }
    
    @Test
    public void testGetLocationFromCoordinates2() {
        assertEquals("Portugal", nominatimUtil.getLocationFromCoordinates(40, -8));
    }
    
    @Test
    public void testGetCoordinatesFromLocation() {
        assertArrayEquals(new Double[] {40.640496, -8.6537841}, nominatimUtil.getCoordinatesFromLocation("Aveiro"));
    }
    
    @Test
    public void testGetLocationFromInvalidCoordinates1() {
        assertEquals("", nominatimUtil.getLocationFromCoordinates(90, 181));
    }
    
    @Test
    public void testGetLocationFromInvalidCoordinates2() {
        assertEquals("", nominatimUtil.getLocationFromCoordinates(-90, -181));
    }
    
    @Test
    public void testRestClientException1() {
        assertEquals("", nominatimUtil.getLocationFromCoordinates(0, 0));
    }
    
    @Test
    public void testRestClientException2() {
        assertArrayEquals(new Double[] {-999.0, -999.0}, nominatimUtil.getCoordinatesFromLocation("TESTE"));
    }
}
