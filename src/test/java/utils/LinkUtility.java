package utils;

import java.net.HttpURLConnection;
import java.net.URL;

public class LinkUtility {
    private static final int MIN_SUCCESS_STATUS_CODE = 200;
    private static final int MAX_REDIRECT_STATUS_CODE = 399;

    public static int getStatusCode(String href) {
        int statusCode = 0;
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) new URL(href).openConnection();
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            connection.connect();
            statusCode = connection.getResponseCode();
        } catch (Exception e) {
            System.out.println("URL kontrol edilirken hata oluştu: " + href);
            System.out.println(e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return statusCode;
    }

    public static boolean isSuccessfulOrRedirect(int statusCode) {
        return statusCode >= MIN_SUCCESS_STATUS_CODE
                && statusCode <= MAX_REDIRECT_STATUS_CODE;
    }
}
