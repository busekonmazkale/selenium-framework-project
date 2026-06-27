package listeners;

import base.BaseTest;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import utils.ScreenshotUtility;

public class TestListener extends TestListenerAdapter {
        private int m_count = 0;

        @Override
        public void onTestFailure(ITestResult tr) {
            System.out.println(">>> HATA YAKALANDI! Listener devrede.");
            System.out.println("Test başarısız oldu, ekran görüntüsü alınıyor: " + tr.getName());
            try {
                String path = ScreenshotUtility.getScreenshot(BaseTest.driver, tr.getName());

            } catch (Exception e) {
                System.err.println("Ekran görüntüsü alınırken hata oluştu: " + e.getMessage());
            }
        }

        private void log(String string) {
            System.out.print(string);
            if (++m_count % 40 == 0) {
                System.out.println(" ");
            }
        }
    }
