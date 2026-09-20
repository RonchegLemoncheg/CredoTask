package Data;

import org.apache.commons.lang3.RandomStringUtils;

public final class RandomTestData {

    public static final String VALID_USERNAME = "valid.user@company.ge";
    public static final String VALID_PASSWORD = "SomePassword1!";

    private static final String INVALID_EMAIL_DOMAIN = "@invalid.ge";
    private static final String MALFORMED_USERNAME_CHARACTERS = "!@#$%^&*()";
    private static final int INVALID_USERNAME_LENGTH = 8;
    private static final int INVALID_PASSWORD_LENGTH = 12;
    private static final int MALFORMED_USERNAME_LENGTH = 16;
    private static final int WRONG_PASSWORD_LENGTH = 10;

    public static String randomInvalidUsername() {
        return RandomStringUtils.randomAlphanumeric(INVALID_USERNAME_LENGTH)
                + INVALID_EMAIL_DOMAIN;
    }

    public static String randomInvalidPassword() {
        return RandomStringUtils.randomAlphanumeric(INVALID_PASSWORD_LENGTH);
    }

    public static String randomMalformedUsername() {
        return RandomStringUtils.random(MALFORMED_USERNAME_LENGTH, MALFORMED_USERNAME_CHARACTERS);
    }

    public static String randomWrongPassword() {
        return RandomStringUtils.randomAlphabetic(WRONG_PASSWORD_LENGTH);
    }

    public static String randomPersonalNumber(int length) {
        return RandomStringUtils.randomNumeric(length);
    }
}
