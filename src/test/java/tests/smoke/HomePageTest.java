package tests.smoke;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

import java.util.List;

public class HomePageTest extends BaseTest {
    @Test(groups = {"smoke"})
    public void homePageShouldOpen() {
        String expectedTitle = ConfigReader.getProperty("title");
        String actualTitle = driver.getTitle();

        logger.info("Expected title: {}", expectedTitle);
        logger.info("Actual title: {}", actualTitle);

        Assert.assertEquals(actualTitle, expectedTitle, "Page title is not as expected."
        );

        logger.info("Home page smoke tests passed.");
    }

    @Test(groups = {"smoke"})
    public void homePageProductsShouldBeVisible() {
        // Initialize the test case for the Extent Report
        test = extent.createTest("Homepage Load Test");
        test.info("Loading the homepage...");

        logger.info("Product visibility test on the homepage has started.");

        List<WebElement> products = driver.findElements(By.cssSelector(".product-image-wrapper"));

        logger.info("Product count on the homepage: {}", products.size());

        Assert.assertTrue(!products.isEmpty(), "No products found on the homepage!");

        logger.info("At least one product is displayed on the homepage.");
        test.pass("Homepage products verified successfully.");
    }
}
