package utils;

import java.net.HttpURLConnection;
import java.net.URL;

public class LinkUtility {
    public static int getStatusCode(String href) {
        int statusCode = 0;

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(href).openConnection();
            connection.setRequestMethod("GET");

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            connection.connect();

            statusCode = connection.getResponseCode();

            connection.disconnect();
        } catch (Exception e) {
            System.out.println("URL kontrol edilirken hata oluştu: " + href);
            System.out.println(e.getMessage());
        }

        return statusCode;
    }
}
