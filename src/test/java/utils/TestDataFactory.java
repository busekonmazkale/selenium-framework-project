package utils;

import net.datafaker.Faker;
import net.datafaker.providers.base.Text;

import static net.datafaker.providers.base.Text.DIGITS;

public class TestDataFactory {
    private static final Faker faker = new Faker();

    public static String generateFirstName() {
        return faker.name().firstName();
    }
    public static String generateLastName(){
        return faker.name().lastName();
    }
    public static String generatePassword(){
        return faker.text().text(Text.TextSymbolsBuilder.builder().len(5).with(DIGITS, 5).build());
    }
    public static String generateEmail(){
        return faker.internet().emailAddress();
    }
}
