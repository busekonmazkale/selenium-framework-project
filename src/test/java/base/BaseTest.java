package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;
import utils.ExtentManager;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    protected static ExtentReports extent;
    protected ExtentTest test;

    public static WebDriver driver;
    protected Logger logger = LoggerFactory.getLogger(getClass());


    @BeforeSuite(alwaysRun = true)
    public void setupSuite() {
        java.util.Locale.setDefault(java.util.Locale.US);
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws MalformedURLException {
        driver = new RemoteWebDriver(
                new URL("http://localhost:4444"),
                new ChromeOptions()
        );
        String url = ConfigReader.getProperty("base.url");
        logger.info("Config dosyasından URL alındı: {}", url);
        driver.manage().window().maximize();
        driver.get(url);
        logger.info("URL açıldı: {}", url);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Tarayıcı kapatıldı.");
        }
    }
    

    @AfterSuite
    public void tearDownSuite() {
        if (extent != null) {
            extent.flush();
            System.out.println("Rapor başarıyla oluşturuldu ve diske yazıldı.");
        }
    }

}
