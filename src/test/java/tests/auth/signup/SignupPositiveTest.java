package tests.auth.signup;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.auth.SignupPage;

public class SignupPositiveTest extends BaseTest {
    @Test(groups = {"auth"})
    public void userShouldSignup() throws InterruptedException {
        SignupPage signupPage = new SignupPage(driver);
        driver.get("https://sauce-demo.myshopify.com/account/register");

        signupPage.enterFirstName("Buse");
        signupPage.enterLastName("Kale");
        signupPage.enterEmail("busekonmaz@outlook.com");
        signupPage.enterPassword("Password123.");
        Thread.sleep(3000);
        signupPage.clickSignupButton();
        Thread.sleep(3000);

        Assert.assertTrue(
                signupPage.isCaptchaDisplayed(),
                "Geçerli signup formu gönderildikten sonra CAPTCHA ekranı görünmedi."
        );
    }
}
