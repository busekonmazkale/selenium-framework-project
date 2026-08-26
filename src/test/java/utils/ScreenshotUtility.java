package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtility {

    public static String getScreenshot(WebDriver driver, String name) throws IOException {

        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);


        Path destination = Path.of(
                System.getProperty("user.dir"),
                "test-output",
                "screenshots",
                name + ".png"
        );

        Files.createDirectories(destination.getParent());
        Files.copy(source.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

        return destination.toString();
    }
}
