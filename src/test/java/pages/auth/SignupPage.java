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

    private By firstNameInput  = By.cssSelector("input[name='customer[first_name]']");
    private By lastNameInput  = By.cssSelector("input[name='customer[last_name]']");
    private By emailInput  = By.cssSelector("input[name='customer[email]']");
    private By passwordInput  = By.cssSelector("input[name='customer[password]']");
    private By signupButton = By.cssSelector("input[value='Create']");
    private By captchaFrame = By.cssSelector("iframe");

    public void enterFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }
    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }
    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }
    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickSignupButton(){
        driver.findElement(signupButton).click();
    }

    public boolean isCaptchaDisplayed() {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(captchaFrame));
            driver.switchTo().defaultContent();
            return true;
        } catch (Exception e) {
            driver.switchTo().defaultContent();
            return false;
        }
    }
}
