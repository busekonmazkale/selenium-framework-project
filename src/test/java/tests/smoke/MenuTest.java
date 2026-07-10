package tests.smoke;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.LinkUtility;

import java.util.List;

public class MenuTest extends BaseTest {
    @Test
    public void MenuItemsTest() {

        List<WebElement> menuItems = driver.findElements(By.cssSelector(".nav navbar-nav li"));

        for (WebElement menuItem : menuItems) {
            WebElement link = menuItem.findElement(By.tagName("a"));

            String menuText = link.getText();
            String href = link.getAttribute("href");


            Assert.assertNotNull(href, menuText + " The menu does not have an href attribute.");
            Assert.assertFalse(href.trim().isEmpty(), menuText + " Menu href value is empty.");

            int statusCode = LinkUtility.getStatusCode(href);

            Assert.assertEquals(
                    statusCode,
                    200,
                    menuText + " Menu returned non-200 status code. URL: " + href + " Status Code: " + statusCode
            );

        }
    }

}
