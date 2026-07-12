package tests.auth.signup;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.auth.SignupPage;
import utils.TestDataFactory;

import java.time.Duration;

public class SignupPositiveTest extends BaseTest {
    private static final String SUCCESS_PAGE_URL = "/signup";
    @Test(groups = {"auth"})
    public void userShouldSignup() throws InterruptedException {
        // Initialize the test case for the Extent Report
        test = extent.createTest("User Signup - Successful Signup Process");
        test.info("Starting the signup process.");

        logger.info("Registration started.");

        SignupPage signupPage = new SignupPage(driver);
        driver.get("https://automationexercise.com/login");

        signupPage.enterName(TestDataFactory.generateFirstName());
        signupPage.enterEmail(TestDataFactory.generateEmail());
        signupPage.clickSignupButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isRedirected = wait.until(ExpectedConditions.urlContains(SUCCESS_PAGE_URL));

        Assert.assertTrue(isRedirected, "Registration failed to redirect to the /signup page.");

        logger.info("Registration completed successfully.");

        test.pass("Signup successful.");
    }
}
