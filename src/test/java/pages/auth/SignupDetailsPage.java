package pages.auth;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupDetailsPage {
    WebDriver driver;
    public SignupDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Arrange - Page Elements
    private By accountInformationTitle = By.cssSelector("div.login-form");
    private By mrRadioButton = By.id("id_gender1");


    // Act - Select Mr Radio Button
    public void selectMrRadioButton() {
        driver.findElement(mrRadioButton).click();
    }

    // Assert - Verify Singup Page Details Title is displayed
    public boolean isSignupDetailsPageDisplayed() {
        return driver.findElement(accountInformationTitle).isDisplayed();
    }
    // Assert - Verify Mr Radio Button is selected
    public boolean isMrRadioButtonSelected() {
        return driver.findElement(mrRadioButton).isSelected();
    }
}
