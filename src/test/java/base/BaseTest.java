package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import utils.ConfigReader;
import utils.ExtentManager;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;


@Listeners(TestListener.class)
public class BaseTest {
    protected static ExtentReports extent;
    protected ExtentTest extentTest;

    public static WebDriver driver;
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public ExtentTest getExtentTest() {
        return extentTest;
    }

    protected String getApplicationUrl(String relativePath) {
        String baseUrl = ConfigReader.getProperty("base.url");
        URI baseUri = URI.create(baseUrl.endsWith("/") ? baseUrl : baseUrl + "/");
        return baseUri.resolve(relativePath).toString();
    }


    @BeforeSuite(alwaysRun = true)
    public void setupSuite() {
        java.util.Locale.setDefault(java.util.Locale.US);
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws MalformedURLException {
        String gridUrl = System.getenv("SELENIUM_GRID_URL");
        if (gridUrl == null || gridUrl.isBlank()) {
            gridUrl = ConfigReader.getProperty("grid.url");
        }

        logger.info("Selenium Grid URL: {}", gridUrl);

        driver = new RemoteWebDriver(
                URI.create(gridUrl).toURL(),
                new ChromeOptions()
        );
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        String url = ConfigReader.getProperty("base.url");
        logger.info("Config dosyasından URL alındı: {}", url);
        driver.manage().window().maximize();
        driver.get(url);
        logger.info("URL opened: {}", url);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed.");
        }
    }


    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        if (extent != null) {
            extent.flush();
            System.out.println("Rapor başarıyla oluşturuldu ve diske yazıldı.");
        }
    }

}
