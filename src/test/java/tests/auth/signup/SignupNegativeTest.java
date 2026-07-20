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

    public void userShouldNotSignup(String scenarioName, String name, String email, InvalidSignupData.InvalidField invalidField){
        SignupPage signupPage = new SignupPage(driver);
        test = extent.createTest("Negative Signup Test");
        test.info("Starting negative the signup process.");
        logger.info("Negative registration started.");

        // Act
        driver.get("https://automationexercise.com/login");
        logger.info("Signup page opened.");

        signupPage.enterName(name);
        signupPage.enterEmail(email);

        String urlBeforeSubmit = driver.getCurrentUrl();
        signupPage.submitSignupForm();
        String urlAfterSubmit = driver.getCurrentUrl();
        logger.info("Signup button clicked.");


        logger.info("URL before submit: {}", urlBeforeSubmit);
        logger.info("URL after submit: {}", driver.getCurrentUrl());
        Assert.assertEquals(urlAfterSubmit, urlBeforeSubmit);

        String validationMessage = switch (invalidField) {
            case NAME ->
                    signupPage.getNameValidationMessage();
            case EMAIL ->
                    signupPage.getEmailValidationMessage();
        };

        logger.info("Validation error message received. Field: {}, Message: {}", invalidField, validationMessage);
        Assert.assertNotNull(validationMessage, scenarioName + "Validation error message returned null.");
        Assert.assertFalse(validationMessage.isBlank(), "Validation error message should not be empty.");
    }
}
