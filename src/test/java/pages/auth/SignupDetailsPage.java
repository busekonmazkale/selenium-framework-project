package pages.auth;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupDetailsPage {
    WebDriverWait wait;
    WebDriver driver;
    public SignupDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Arrange - Page Elements
    private By accountInformationTitle = By.cssSelector("div.login-form");
    private By mrRadioButton = By.id("id_gender1");
    private By enterPassword = By.id("password");
    private By daySelect = By.id("days");
    private final By monthSelect = By.id("months");
    private final By yearSelect = By.id("years");

    // Act - Select Mr Radio Button
    public void selectMrRadioButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(mrRadioButton)).click();
    }
    // Act - Enter Password
    public void enterPassword(String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterPassword)).sendKeys(password);
    }

    // Act - Select day of birth
    public void selectDateOfBirth(String day, String month, String year) {
        selectByVisibleText(daySelect, day);
        selectByVisibleText(monthSelect, month);
        selectByVisibleText(yearSelect, year);
    }

    private void selectByVisibleText(By locator, String visibleText) {
        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
        new Select(dropdown).selectByVisibleText(visibleText);
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
