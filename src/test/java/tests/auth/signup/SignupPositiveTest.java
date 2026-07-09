package tests.auth.signup;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.auth.SignupPage;
import utils.TestDataFactory;

public class SignupPositiveTest extends BaseTest {
    @Test(groups = {"auth"})
    public void userShouldSignup() throws InterruptedException {
        SignupPage signupPage = new SignupPage(driver);
        driver.get("https://automationexercise.com/login");

        signupPage.enterName(TestDataFactory.generateFirstName());
        signupPage.enterEmail(TestDataFactory.generateEmail());

        Thread.sleep(3000);
        signupPage.clickSignupButton();
        Thread.sleep(3000);
    }
}
