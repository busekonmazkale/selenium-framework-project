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
        driver.findElement(nameInput).sendKeys(firstName);
    }
    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }
    public void clickSignupButton(){
        driver.findElement(signupButton).click();
    }
}
