package lei.tqs.web;

import java.util.concurrent.TimeUnit;
import lei.tqs.HomeworkApplication;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HomeworkApplication.class) 
@TestPropertySource(value={"classpath:application.properties"})
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class SeleniumExportedFromIDETest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testMadrid() throws Exception {
        TQSHomePage page = new TQSHomePage(driver, verificationErrors);
        page.searchWeather("Madrid", "01/05/2019", "06/05/2019");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.textToBe(By.id("city_header"), "Madrid"));
        page.assertEqualsTextInElementByID("Madrid", "city_header");
        page.assertEqualsTextInElementByID("TERÇA", "day_0");
        page.assertEqualsTextInElementByID("1 Maio", "date_1");
        page.assertEqualsTextInElementByID("Ligeiramente nublado começa esta tarde, continua até à noite.", "summary_2");
        page.assertEqualsTextInElementByID("23° / 7°", "temperature_3");
        page.assertEqualsTextInElementByID("  1.49 m/s", "wind_4");
    }
    
    @Test
    public void testPorto() throws Exception {
        TQSHomePage page = new TQSHomePage(driver, verificationErrors);
        page.searchWeather("Porto", "01/05/2019", "06/05/2019");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.textToBe(By.id("city_header"), "Porto"));
        page.assertEqualsTextInElementByID("Porto", "city_header");
        for (int i = 0; i < 5; i++)
            page.assertEqualsTextInElementByID(new String[] {"QUARTA", "QUINTA", "SEXTA", "SÁBADO", "DOMINGO"}[i], "day_"+i);
        page.assertEqualsTextInElementByID("2 Maio", "date_1");
        page.assertEqualsTextInElementByID("Ligeiramente nublado até tarde.", "summary_2");
        page.assertEqualsTextInElementByID("23° / 9°", "temperature_3");
        page.assertEqualsTextInElementByID("  1.46 m/s", "wind_4");
    }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
