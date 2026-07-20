package pages.auth;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupPage {

    WebDriverWait wait;
    WebDriver driver;
    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By nameInput  = By.cssSelector("[data-qa='signup-name']");
    private By emailInput  = By.cssSelector("[data-qa='signup-email']");
    private By signupButton = By.cssSelector("[data-qa='signup-button']");

    public void enterName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(firstName);
    }
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
    }
    public SignupDetailsPage clickSignupButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(signupButton)).click();
        return new SignupDetailsPage(driver);
    }
    public String getNameValidationMessage() {
        return driver.findElement(nameInput).getDomProperty("validationMessage");
    }
    public String getEmailValidationMessage() {
        return driver.findElement(emailInput).getDomProperty("validationMessage");
    }
    public void submitSignupForm() {
        wait.until(ExpectedConditions.elementToBeClickable(signupButton)).click();
    }
}
