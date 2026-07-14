package pages.auth;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupDetailsPage {
    WebDriver driver;

    public SignupDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    private By accountInformationTitle = By.cssSelector("div.login-form");
    private By mrRadioButton = By.id("id_gender1");


    public void selectMrRadioButton() {
        driver.findElement(mrRadioButton).click();
    }

    public boolean isMrRadioButtonSelected() {
        return driver.findElement(mrRadioButton).isSelected();
    }


    public boolean isSignupDetailsPageDisplayed() {
        return driver.findElement(accountInformationTitle).isDisplayed();
    }

}
