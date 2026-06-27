package utils;

import org.apache.maven.surefire.shared.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtility {

    public static String getScreenshot(WebDriver driver, String name) throws IOException {

        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);


        String path = System.getProperty("user.dir") + "/test-output/screenshots/" + name + ".png";
        File destination = new File(path);

        FileUtils.copyFile(source, destination);

        return path;
    }
}
