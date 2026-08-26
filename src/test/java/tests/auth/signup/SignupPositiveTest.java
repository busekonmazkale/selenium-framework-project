package tests.auth.signup;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.auth.SignupDetailsPage;
import pages.auth.SignupPage;
import utils.TestDataFactory;

public class SignupPositiveTest extends BaseTest {
    private SignupDetailsPage signupDetailsPage;
    private boolean accountCreationSubmitted;

    @Test(groups = {"auth"})
    public void userShouldSignup() {

        extentTest = extent.createTest("Signup Test");
        extentTest.info("Starting the signup process.");
        logger.info("Positive signup flow started.");

        SignupPage signupPage = new SignupPage(driver);

        // Start registration with unique user data
        driver.get(getApplicationUrl("login"));
        signupPage.enterName(TestDataFactory.generateFirstName());
        signupPage.enterEmail(TestDataFactory.generateEmail());
        signupDetailsPage = signupPage.clickSignupButton();

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
        signupDetailsPage.enterPassword(TestDataFactory.generatePassword());
        signupDetailsPage.selectDateOfBirth("15", "May", "1996");
        signupDetailsPage.enterName(TestDataFactory.generateFirstName());
        signupDetailsPage.enterLastName(TestDataFactory.generateLastName());
        signupDetailsPage.enterCompanyName(TestDataFactory.generateCompanyName());
        signupDetailsPage.enterAddress(TestDataFactory.generateAddress());
        signupDetailsPage.selectCountry("India");
        signupDetailsPage.enterState(TestDataFactory.generateState());
        signupDetailsPage.enterCity(TestDataFactory.generateCity());
        signupDetailsPage.enterZipcode(TestDataFactory.generateZipcode());
        signupDetailsPage.enterMobileNumber(TestDataFactory.generateMobileNumber());

        signupDetailsPage.clickCreateAccountButton();
        accountCreationSubmitted = true;

        // Verify successful account creation
        Assert.assertTrue(
                signupDetailsPage.isAccountCreatedMessageDisplayed(),
                "Account created confirmation message is not displayed."
        );

        logger.info("Positive signup flow completed successfully.");
    }

    @AfterMethod(alwaysRun = true)
    public void deleteCreatedAccount() {
        if (!accountCreationSubmitted || signupDetailsPage == null) {
            return;
        }

        try {
            signupDetailsPage.deleteCreatedAccount();
            Assert.assertTrue(
                    signupDetailsPage.isAccountDeletedMessageDisplayed(),
                    "Account deleted confirmation message is not displayed."
            );
            logger.info("Created test account deleted successfully.");
        } finally {
            accountCreationSubmitted = false;
            signupDetailsPage = null;
        }
    }
}
