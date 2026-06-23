package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

public class BaseTest {

    protected WebDriver driver;
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @BeforeMethod
    public void setUp() {
        String url = ConfigReader.getProperty("base.url");
        logger.info("Config dosyasından URL alındı: {}", url);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        logger.info("URL açıldı: {}", url);

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Tarayıcı kapatıldı.");
        }
    }

}
