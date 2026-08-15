package utils.dataprovider;

import org.testng.annotations.DataProvider;

public final class InvalidSignupData {
    private static final String VALID_NAME = "Buse Kale";
    private static final String VALID_EMAIL = "buse.kale@example.com";
    private static final String MALFORMED_EMAIL = "buse.kale.example.com";

    private InvalidSignupData() {
        // Utility class; it should not be instantiated.
    }

    @DataProvider(name = "invalidSignupData")
    public static Object[][] invalidSignupData() {
        return new Object[][]{
                {"Name is empty", "", VALID_EMAIL, InvalidField.NAME},
                {"Email is empty", VALID_NAME, "", InvalidField.EMAIL},
                {"Email format is invalid", VALID_NAME, MALFORMED_EMAIL, InvalidField.EMAIL}
        };
    }

    public enum InvalidField {
        NAME,
        EMAIL
    }
}
