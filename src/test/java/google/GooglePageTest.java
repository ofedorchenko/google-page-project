package google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GooglePageTest {

    private WebDriver driver;
    private String urlGoogle = "https://www.google.com/";

    @BeforeTest
    public void annotationBeforeTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); // Setting system properties of ChromeDriver
        driver = new ChromeDriver();
    }

    // Successful name check in Google logo
    @Test
    public void successfulGoogleLogoName() {
        driver.get(urlGoogle);
        // Checks if there is a "Search Google" button on the page
        WebElement logoText = driver.findElement(By.xpath("//div[@class = 'k1zIA rSk4se']/img"));
        Assert.assertEquals(logoText.getAttribute("alt"), "Google");
    }

    // Unsuccessful name check in Google logo
    @Test
    public void unsuccessfulGoogleLogoName() {
        driver.get(urlGoogle);
        // Checks if there is a "Search Google" button on the page
        WebElement logoText = driver.findElement(By.xpath("//div[@class = 'k1zIA rSk4se']/img"));
        Assert.assertEquals(logoText.getAttribute("alt"), "Gooooogle");
    }

    @AfterTest
    public void annotationAfterTest() {
        driver.quit();
    }
}
