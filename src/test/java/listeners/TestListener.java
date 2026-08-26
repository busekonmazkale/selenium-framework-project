package listeners;

import base.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import utils.ScreenshotUtility;

public class TestListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest extentTest = getExtentTest(result);

        if (extentTest != null) {
            Throwable failure = result.getThrowable();
            if (failure != null) {
                extentTest.fail(failure);
            } else {
                extentTest.fail("Test failed without an exception.");
            }
        }

        if (BaseTest.driver == null) {
            return;
        }

        try {
            String screenshotName = result.getName() + "-" + System.currentTimeMillis();
            String screenshotPath = ScreenshotUtility.getScreenshot(
                    BaseTest.driver,
                    screenshotName
            );

            if (extentTest != null) {
                extentTest.addScreenCaptureFromPath(screenshotPath);
            }
        } catch (Exception e) {
            System.err.println("Failed to capture or attach screenshot: " + e.getMessage());
        }
    }

    private ExtentTest getExtentTest(ITestResult result) {
        Object testInstance = result.getInstance();
        if (testInstance instanceof BaseTest baseTest) {
            return baseTest.getExtentTest();
        }
        return null;
    }
}
