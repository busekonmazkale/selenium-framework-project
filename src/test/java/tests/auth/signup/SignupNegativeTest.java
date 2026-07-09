package tests.auth.signup;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.auth.SignupPage;
import utils.TestDataFactory;

public class SignupNegativeTest extends BaseTest {
    @Test(groups = {"auth"})
    public void userShouldNotSignup() throws InterruptedException {
        SignupPage signupPage = new SignupPage(driver);
        driver.get("https://sauce-demo.myshopify.com/account/register");


        signupPage.enterName(TestDataFactory.generateFirstName());
        signupPage.clickSignupButton();
        Thread.sleep(5000);

    }
}
