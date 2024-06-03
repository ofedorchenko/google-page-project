package google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GooglePageTest {

    private WebDriver driver;
    private String urlGoogle = "https://www.google.com/";

    @BeforeTest
    public void annotationBeforeTest() {
        String chromedriverPath = System.getenv("CHROMEDRIVER_PATH");
        System.out.println("CHROMEDRIVER_PATH=" + chromedriverPath);

        // For Docker
        System.setProperty("webdriver.chrome.driver", chromedriverPath); // Setting system properties of ChromeDriver
        // For local
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--remote-debugging-port=9222");

        driver = new ChromeDriver(options);
    }

    // Successful name check in Google logo
    @Test
    public void successfulGoogleLogoName() {
        driver.get(urlGoogle);
        // Checks if there is a "Search Google" button on the page
        WebElement logoText = driver.findElement(By.xpath("//div[@class = 'k1zIA rSk4se']/img"));
        assertEquals(logoText.getAttribute("alt"), "Google");
    }

    // Unsuccessful name check in Google logo
    @Test
    public void unsuccessfulGoogleLogoName() {
        driver.get(urlGoogle);
        // Checks if there is a "Search Google" button on the page
        WebElement logoText = driver.findElement(By.xpath("//div[@class = 'k1zIA rSk4se']/img"));
        assertEquals(logoText.getAttribute("alt"), "Gooooogle");
    }

    @AfterTest
    public void annotationAfterTest() {
        driver.quit();
    }
}
