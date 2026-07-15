package tests.auth.signup;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.auth.SignupDetailsPage;

public class SignupDetailsPositiveTest extends BaseTest {
    @Test(groups = {"auth"})
    public void userSignupDetails(){
        SignupDetailsPage signupDetailsPage = new SignupDetailsPage(driver);
    }
}
