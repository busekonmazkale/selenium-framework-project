package pages.auth;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupDetailsPage {
    WebDriver driver;
    public SignupDetailsPage(WebDriver driver) {
        this.driver = driver;
    }
    private By accountInformationTitle = By.cssSelector("div.login-form");
    public boolean isSignupDetailsPageDisplayed() {

        return driver
                .findElement(accountInformationTitle)
                .isDisplayed();
    }

}
