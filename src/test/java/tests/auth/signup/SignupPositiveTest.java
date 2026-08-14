package tests.auth.signup;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.auth.SignupDetailsPage;
import pages.auth.SignupPage;
import utils.TestDataFactory;

public class SignupPositiveTest extends BaseTest {
    @Test(groups = {"auth"})
    public void userShouldSignup() {

        test = extent.createTest("Signup Test");
        test.info("Starting the signup process.");
        logger.info("Positive signup flow started.");

        SignupPage signupPage = new SignupPage(driver);

        // Start registration with unique user data
        driver.get("https://automationexercise.com/login");
        signupPage.enterName(TestDataFactory.generateFirstName());
        signupPage.enterEmail(TestDataFactory.generateEmail());
        SignupDetailsPage signupDetailsPage = signupPage.clickSignupButton();

        // Verify navigation to the account details form
        Assert.assertTrue(
                signupDetailsPage.isSignupDetailsPageDisplayed(),
                "Signup details page is not displayed."
        );

        logger.info("Signup details page is displayed.");

        signupDetailsPage.selectMrRadioButton();

        // Verify that the selected title is retained
        Assert.assertTrue(
                signupDetailsPage.isMrRadioButtonSelected(),
                "Mr. radio button is not selected."
        );

        // Complete the required account and address details
        signupDetailsPage.enterPassword("SecurePass123!");
        signupDetailsPage.selectDateOfBirth("15", "May", "1996");
        signupDetailsPage.enterName("Buse");
        signupDetailsPage.enterLastName("Kale");
        signupDetailsPage.enterCompanyName("Lorem Ipsum Ltd.");
        signupDetailsPage.enterAddress("123 Atatürk Street, Istanbul");
        signupDetailsPage.selectCountry("India");
        signupDetailsPage.enterState("Maharashtra");
        signupDetailsPage.enterCity("Mumbai");
        signupDetailsPage.enterZipcode("400001");
        signupDetailsPage.enterMobileNumber("9876543210");

        signupDetailsPage.clickCreateAccountButton();

        // Verify successful account creation
        Assert.assertTrue(
                signupDetailsPage.isAccountCreatedMessageDisplayed(),
                "Account created confirmation message is not displayed."
        );

        logger.info("Positive signup flow completed successfully.");
    }
}
