package tests.smoke;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.components.HeaderMenu;
import utils.ConfigReader;

import java.time.Duration;
import java.util.List;

public class HeaderMenuTest extends BaseTest {
    @Test(groups = {"smoke"})
    public void headerMenuLinksShouldBeDisplayedAndOpenCorrectUrls() {
        extentTest = extent.createTest("Header Menu Test");
        extentTest.info("Starting header menu link validation.");

        HeaderMenu headerMenu = new HeaderMenu(driver);

        List<WebElement> headerMenuLinks = headerMenu.getMenuLinks();

        Assert.assertFalse(headerMenuLinks.isEmpty(), "Header Menu Links Size:" + headerMenuLinks.size());

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

        List<String> actualMenuTexts = headerMenuLinks.stream()
                .map(WebElement::getText)
                // Removes private-use icon characters and surrounding whitespace.
                .map(text -> text.replaceAll("\\p{Co}", "").trim())
                .toList();

        Assert.assertEquals(actualMenuTexts,expectedMenuTexts,  "Header menu links do not match the expected links.");

        String baseUrl = ConfigReader.getProperty("base.url");

        List<String> expectedUrls = List.of(
                baseUrl,
                baseUrl + "products",
                baseUrl + "view_cart",
                baseUrl + "login",
                baseUrl + "test_cases",
                baseUrl + "api_list",
                "https://www.youtube.com/c/AutomationExercise",
                baseUrl + "contact_us"
        );
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (int i = 0; i < expectedUrls.size(); i++) {
            driver.get(baseUrl);

            // Finds the elements again after each navigation to avoid stale references.
            List<WebElement> currentMenuLinks = headerMenu.getMenuLinks();
            String menuText = expectedMenuTexts.get(i);
            String expectedUrl = expectedUrls.get(i);

            currentMenuLinks.get(i).click();
            wait.until(webDriver ->
                    removeFragment(webDriver.getCurrentUrl())
                            .equals(removeFragment(expectedUrl))
            );

            Assert.assertEquals(
                    removeFragment(driver.getCurrentUrl()),
                    removeFragment(expectedUrl),
                    "Incorrect URL opened for the '" + menuText + "' menu item."
            );
        }

        extentTest.pass("Header menu links were validated successfully.");
    }

    private static String removeFragment(String url) {
        int fragmentIndex = url.indexOf('#');
        return fragmentIndex >= 0
                ? url.substring(0, fragmentIndex)
                : url;
    }
}
