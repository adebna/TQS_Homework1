package lei.tqs.web;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TQSHomePage {
    private WebDriver driver;
    private StringBuffer verificationErrors;

    public TQSHomePage(WebDriver driver, StringBuffer verificationErrors) {
        this.driver = driver;
        String baseUrl = "http://localhost:8080/home/";
        driver.get(baseUrl);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.textToBe(By.id("city_header"), "Aveiro"));
    }

    public void searchWeather(String location, String begin_date, String end_date) {
        try {
            //driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='OK'])[2]/following::form[1]")).click();
            driver.findElement(By.id("city_name")).clear();
            driver.findElement(By.id("city_name")).sendKeys(location);
            driver.findElement(By.id("begin_timestamp")).clear();
            driver.findElement(By.id("begin_timestamp")).sendKeys(begin_date);
            driver.findElement(By.id("end_timestamp")).clear();
            driver.findElement(By.id("end_timestamp")).sendKeys(end_date);
            driver.findElement(By.id("search_button")).click();
        } catch (RuntimeException e) {
            takeScreenShot(e, "searchError");
        }
    }

    public void assertEqualsTextInElementByID(String expected, String element_id) {
        try {
            assertEquals(expected, driver.findElement(By.id(element_id)).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }
    
    private void takeScreenShot(RuntimeException e, String fileName) {
        File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenShot, new File(fileName + ".png"));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage(), ioe);
        }
        throw e;
    }
}
