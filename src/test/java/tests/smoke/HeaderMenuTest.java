package tests.smoke;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.components.HeaderMenu;
import utils.ConfigReader;

import java.time.Duration;
import java.util.List;

public class HeaderMenuTest extends BaseTest {
    @Test
    public void headerMenuLinksShouldBeDisplayedAndOpenCorrectUrls() {
        // Creates a HeaderMenu instance using the active WebDriver.
        HeaderMenu headerMenu = new HeaderMenu(driver);

        // Get the menu links displayed in the header.
        List<WebElement> headerMenuLinks = headerMenu.getMenuLinks();

        // Prints the total number of menu links found to the console.
        System.out.println("MENU" + headerMenuLinks.size());

        // Verifies that the menu links list is not empty.
        Assert.assertFalse(headerMenuLinks.isEmpty(), "Header Menu Links Size:" + headerMenuLinks.size());

        // Defines the menu link texts expected to appear in the header.
        List<String> expectedMenuTexts = List.of(
                "Home",
                "Products",
                "Cart",
                "Signup / Login",
                "Test Cases",
                "API Testing",
                "Video Tutorials",
                "Contact us"
        );

        // Extracts and normalizes the visible text from each menu link.
        List<String> actualMenuTexts = headerMenuLinks.stream()
                .map(WebElement::getText)
                // Removes private-use icon characters and surrounding whitespace.
                .map(text -> text.replaceAll("\\p{Co}", "").trim())
                .toList();

        // Verifies that the actual menu texts match the expected texts and order.
        Assert.assertEquals(actualMenuTexts,expectedMenuTexts,  "Header menu links do not match the expected links.");

        // Gets the application base URL from the configuration file.
        String baseUrl = ConfigReader.getProperty("base.url");

        // Defines the expected URLs in the same order as the header menu items.
        List<String> expectedUrls = List.of(
                baseUrl + "#google_vignette",
                baseUrl + "products",
                baseUrl + "view_cart",
                baseUrl + "login",
                baseUrl + "test_cases",
                baseUrl + "api_list",
                "https://www.youtube.com/c/AutomationExercise",
                baseUrl + "contact_us"
        );
        System.out.println(expectedUrls);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Clicks every menu item and verifies that it opens the expected URL.
        for (int i = 0; i < expectedUrls.size(); i++) {
            driver.get(baseUrl);

            // Finds the elements again after each navigation to avoid stale references.
            List<WebElement> currentMenuLinks = headerMenu.getMenuLinks();
            String menuText = expectedMenuTexts.get(i);
            String expectedUrl = expectedUrls.get(i);

            currentMenuLinks.get(i).click();
            wait.until(ExpectedConditions.urlToBe(expectedUrl));

            Assert.assertEquals(
                    driver.getCurrentUrl(),
                    expectedUrl,
                    "Incorrect URL opened for the '" + menuText + "' menu item."
            );
        }
    }
}
