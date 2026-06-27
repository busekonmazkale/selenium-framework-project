package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;
import utils.ExtentManager;

public class BaseTest {
    protected ExtentReports extent;
    protected ExtentTest test;

    public static WebDriver driver;
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @BeforeSuite
    public void setupSuite() {
        java.util.Locale.setDefault(java.util.Locale.US);
        extent = ExtentManager.getInstance();
    }

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

    @AfterSuite
    public void tearDownSuite() {
        if (extent != null) {
            extent.flush();
            System.out.println("Rapor başarıyla oluşturuldu ve diske yazıldı.");
        }
    }

}
