package tests.auth.signup;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.auth.SignupDetailsPage;
import pages.auth.SignupPage;
import utils.TestDataFactory;

import java.time.Duration;

public class SignupPositiveTest extends BaseTest {
    private static final String SUCCESS_PAGE_URL = "/signup";

    @Test(groups = {"auth"})
    public void userShouldSignup() {

        test = extent.createTest("Signup Test");
        test.info("Starting the signup process.");
        logger.info("Positive signup flow started.");

        SignupPage signupPage = new SignupPage(driver);

        // Act - Fill in the signup form and submit it
        driver.get("https://automationexercise.com/login");
        signupPage.enterName(TestDataFactory.generateFirstName());
        signupPage.enterEmail(TestDataFactory.generateEmail());
        SignupDetailsPage signupDetailsPage = signupPage.clickSignupButton();

        boolean isSignupDetailsPageDisplayed = signupDetailsPage.isSignupDetailsPageDisplayed();

        // Assert - Verify that the Signup Details page is displayed
        Assert.assertTrue(
                isSignupDetailsPageDisplayed,
                "Signup details page is not displayed."
        );

        logger.info("Signup details page displayed: {}", isSignupDetailsPageDisplayed);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isRedirected = wait.until(ExpectedConditions.urlContains(SUCCESS_PAGE_URL));

        Assert.assertTrue(
                isRedirected,
                "Registration failed to redirect to the /signup page."
        );

        // Act - Select Mr Radio Button
        signupDetailsPage.selectMrRadioButton();

        // Assert - Verify Mr Radio Button
        Assert.assertTrue(
                signupDetailsPage.isMrRadioButtonSelected(),
                "Mr. radio button was not selected."
        );
    }
}
