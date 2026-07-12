package utils.dataprovider;

import org.testng.annotations.DataProvider;

public class InvalidSignupData {
    private static final String VALID_NAME = "Test";
    private static final String VALID_EMAIL = "test@test.com";
    private static final String INVALID_EMAIL = "test.com";

    @DataProvider(name = "invalidSignupData")
    public static Object[][] signupData(){
        return new Object[][] {
                {"Empty name", "", VALID_EMAIL, InvalidField.NAME},
                {"Empty email", VALID_NAME, "", InvalidField.EMAIL},
                {
                        "Email format is invalid",
                        VALID_NAME,
                        INVALID_EMAIL,
                        InvalidField.EMAIL
                }
        };
    }
    public enum InvalidField {
        NAME,
        EMAIL
    }
}
