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

        List<WebElement> menuItems = driver.findElements(By.cssSelector("#main-menu li"));

        for (WebElement menuItem : menuItems) {
            WebElement link = menuItem.findElement(By.tagName("a"));

            String menuText = link.getText();
            String href = link.getAttribute("href");


            Assert.assertNotNull(href, menuText + " menüsünün href değeri null geldi.");
            Assert.assertFalse(href.trim().isEmpty(), menuText + " menüsünün href değeri boş geldi.");

            int statusCode = LinkUtility.getStatusCode(href);

            Assert.assertEquals(
                    statusCode,
                    200,
                    menuText + " menüsü 200 dönmedi. URL: " + href + " Status Code: " + statusCode
            );

        }
    }

}
