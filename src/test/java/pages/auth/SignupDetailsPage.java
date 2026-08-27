package pages.auth;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    private final By accountInformationTitle = By.cssSelector("div.login-form");
    private final By mrRadioButton = By.id("id_gender1");
    private final By enterPassword = By.id("password");
    private final By daySelect = By.id("days");
    private final By monthSelect = By.id("months");
    private final By yearSelect = By.id("years");
    private final By enterName = By.id("first_name");
    private final By enterLastName = By.id("last_name");
    private final By enterCompanyName = By.id("company");
    private final By enterAddress = By.id("address1");
    private final By countrySelect = By.id("country");
    private final By enterState = By.id("state");
    private final By enterCity = By.id("city");
    private final By enterZipcode = By.id("zipcode");
    private final By enterMobileNumber = By.id("mobile_number");
    private final By createAccountButton = By.cssSelector("[data-qa='create-account']");
    private final By accountCreatedTitle = By.cssSelector("[data-qa='account-created']");
    private final By continueButton = By.cssSelector("[data-qa='continue-button']");
    private final By deleteAccountLink = By.cssSelector("a[href='/delete_account']");
    private final By accountDeletedTitle = By.cssSelector("[data-qa='account-deleted']");

    // Form actions
    public void selectMrRadioButton() {
        WebElement radioButton = wait.until(ExpectedConditions.presenceOfElementLocated(mrRadioButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioButton);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterPassword)).sendKeys(password);
    }

    public void selectDateOfBirth(String day, String month, String year) {
        selectByVisibleText(daySelect, day);
        selectByVisibleText(monthSelect, month);
        selectByVisibleText(yearSelect, year);
    }

    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterName)).sendKeys(name);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterLastName)).sendKeys(lastName);
    }

    public void enterCompanyName(String companyName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterCompanyName)).sendKeys(companyName);
    }

    public void enterAddress(String address) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterAddress)).sendKeys(address);
    }

    public void selectCountry(String country) {
        selectByVisibleText(countrySelect, country);
    }

    public void enterState(String state) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterState)).sendKeys(state);
    }

    public void enterCity(String city) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterCity)).sendKeys(city);
    }

    public void enterZipcode(String zipcode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterZipcode)).sendKeys(zipcode);
    }

    public void enterMobileNumber(String mobileNumber) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterMobileNumber)).sendKeys(mobileNumber);
    }

    public void clickCreateAccountButton() {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(createAccountButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

    // Page state
    public boolean isSignupDetailsPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accountInformationTitle)).isDisplayed();
    }

    public boolean isMrRadioButtonSelected() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(mrRadioButton)).isSelected();
    }

    public boolean isAccountCreatedMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accountCreatedTitle)).isDisplayed();
    }

    public void deleteCreatedAccount() {
        clickWithJavaScript(continueButton);
        clickWithJavaScript(deleteAccountLink);
    }

    public boolean isAccountDeletedMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accountDeletedTitle)).isDisplayed();
    }

    // Helper methods
    private void clickWithJavaScript(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private void selectByVisibleText(By locator, String visibleText) {
        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
        new Select(dropdown).selectByVisibleText(visibleText);
    }
}
