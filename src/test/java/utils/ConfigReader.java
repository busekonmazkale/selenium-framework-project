package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new IllegalStateException(
                        "config.properties classpath üzerinde bulunamadı."
                );
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new ExceptionInInitializerError(
                    "config.properties okunamadı: " + e.getMessage()
            );
        }
    }

    private ConfigReader() {
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
