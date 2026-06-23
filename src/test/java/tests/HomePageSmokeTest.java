package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

import java.util.List;

public class HomePageSmokeTest extends BaseTest {
    @Test
    public void homePageShouldOpen() {
        String expectedTitle = ConfigReader.getProperty("title");
        String actualTitle = driver.getTitle();

        logger.info("Beklenen title: {}", expectedTitle);
        logger.info("Gerçek title: {}", actualTitle);

        Assert.assertEquals(actualTitle, expectedTitle, "Sayfa title beklenen gibi değil."
        );

        logger.info("Home page smoke test çalıştı.");
    }

    @Test
    public void homePageProductsShouldBeVisible() {
        logger.info("Ana sayfa ürün görünürlük testi başladı.");

        List<WebElement> products = driver.findElements(By.cssSelector(".four.columns"));

        logger.info("Ana sayfada bulunan ürün sayısı: {}", products.size());

        Assert.assertTrue(!products.isEmpty(), "Ana sayfada ürün bulunamadı.");

        logger.info("Ana sayfada en az 1 ürün listelendi.");
    }
}
