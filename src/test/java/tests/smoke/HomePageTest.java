package tests.smoke;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

import java.time.Duration;
import java.util.List;

public class HomePageTest extends BaseTest {
    @Test(groups = {"smoke"})
    public void homePageTitleShouldBeCorrect() {
        test = extent.createTest("Homepage Title Test");
        test.info("Homepage title is being verified.");

        String expectedTitle = ConfigReader.getProperty("title");

        Assert.assertNotNull(
                expectedTitle,
                "'title' property is missing from config.properties."
        );

        Assert.assertFalse(
                expectedTitle.isBlank(),
                "'title' property cannot be empty in config.properties."
        );

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs(expectedTitle));

        String actualTitle = driver.getTitle();

        logger.info("Expected title: {}", expectedTitle);
        logger.info("Actual title: {}", actualTitle);

        test.info("Expected title: " + expectedTitle);
        test.info("Actual title: " + actualTitle);

        Assert.assertEquals(actualTitle, expectedTitle, "Homepage title does not match the configured title.");

        test.pass("Homepage title verified successfully.");
    }

    @Test(groups = {"smoke"})
    public void homePageProductsShouldBeVisible() {
        // Initialize the test case for the Extent Report
        test = extent.createTest("Homepage Load Test");
        test.info("Loading the homepage products...");

        logger.info("Product visibility test on the homepage has started.");

        By productLocator = By.cssSelector(".product-image-wrapper");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(productLocator));

        List<WebElement> products = driver.findElements(By.cssSelector(".product-image-wrapper")).stream().filter(WebElement::isDisplayed).toList();

        logger.info("Product count on the homepage: {}", products.size());

        Assert.assertFalse(products.isEmpty(), "No products found on the homepage!");

        logger.info("At least one product is displayed on the homepage.");
        test.pass("Homepage products verified successfully.");
    }
}
