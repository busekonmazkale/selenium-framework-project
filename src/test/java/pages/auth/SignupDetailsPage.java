package pages.auth;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupDetailsPage {
    WebDriverWait wait;
    WebDriver driver;
    public SignupDetailsPage(WebDriver driver) {
        this.driver = driver;
        wait.until(ExpectedConditions.visibilityOfElementLocated(
    }

    // Arrange - Page Elements
    private By accountInformationTitle = By.cssSelector("div.login-form");
    private By mrRadioButton = By.id("id_gender1");


    // Act - Select Mr Radio Button
    public void selectMrRadioButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(mrRadioButton)).click();
    }

    // Assert - Verify Singup Page Details Title is displayed
    public boolean isSignupDetailsPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accountInformationTitle)).isDisplayed();
    }
    // Assert - Verify Mr Radio Button is selected
    public boolean isMrRadioButtonSelected() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(mrRadioButton)).isSelected();
    }
}
