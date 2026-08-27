package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HeaderMenu {
    WebDriverWait wait;
    WebDriver driver;
    public HeaderMenu(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By menuLinks = By.cssSelector(".shop-menu .nav.navbar-nav li a");

    public List<WebElement> getMenuLinks() {
        return wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(menuLinks)
        );
    }
}
