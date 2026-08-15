package tests.auth.signup;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.auth.SignupPage;
import utils.dataprovider.InvalidSignupData;

public class SignupNegativeTest extends BaseTest {
    @Test(
            groups = {"auth"},
            dataProvider = "invalidSignupData",
            dataProviderClass = InvalidSignupData.class
    )
    public void userShouldNotSignup(
            String scenarioName,
            String name,
            String email,
            InvalidSignupData.InvalidField invalidField
    ) {
        test = extent.createTest("Negative Signup Test");
        test.info("Starting the negative signup scenario: " + scenarioName);
        logger.info("Negative registration started.");

        SignupPage signupPage = new SignupPage(driver);

        // Submit the signup form with invalid test data
        driver.get("https://automationexercise.com/login");
        logger.info("Signup page opened.");
        signupPage.enterName(name);
        signupPage.enterEmail(email);

        String urlBeforeSubmit = driver.getCurrentUrl();
        signupPage.submitSignupForm();
        String urlAfterSubmit = driver.getCurrentUrl();

        // Read the validation message from the field under test
        String validationMessage = switch (invalidField) {
            case NAME -> signupPage.getNameValidationMessage();
            case EMAIL -> signupPage.getEmailValidationMessage();
        };

        logger.info("Signup button clicked.");
        logger.info("URL before submit: {}", urlBeforeSubmit);
        logger.info("URL after submit: {}", urlAfterSubmit);
        logger.info("Validation error message received. Field: {}, Message: {}", invalidField, validationMessage);

        // Verify that registration is blocked by browser validation
        Assert.assertEquals(urlAfterSubmit, urlBeforeSubmit, "The signup form should not be submitted.");
        Assert.assertNotNull(validationMessage, scenarioName + ": validation message returned null.");
        Assert.assertFalse(validationMessage.isBlank(), "Validation error message should not be empty.");
    }
}
