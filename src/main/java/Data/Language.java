package Data;

public enum Language {

    GEORGIAN(
            "ქართული",
            "//li[.//img[contains(@src, 'georgia')]]",
            "სავალდებულო ველი",
            "მონაცემები არასწორია",
            "დასაშვებია მხოლოდ 11 სიმბოლო",
            "პირი არ არის სრულწლოვანი"
    ),
    ENGLISH(
            "English",
            "//li[.//img[contains(@src, 'united%20kingdom')]]",
            "Required field",
            "Please make sure the entered details are correct",
            "Only 11 characters are allowed",
            "This person is not of legal age, please check its information and try again"
    ),
    RUSSIAN(
            "Русский",
            "//li[.//img[contains(@src, 'russia')]]",
            "Обязательное поле",
            "Пожалуйста, убедитесь, что введенные данные верны.",
            "Допускается только 11 символов",
            "Пользователь является несовершеннолетним. Пожалуйста, проверьте информацию и попробуйте снова"
    );

    private final String label;
    private final String optionXPath;
    private final String requiredFieldMessage;
    private final String errorToastMessage;
    private final String personalNumberLengthMessage;
    private final String underageErrorToastMessage;

    Language(
            String label,
            String optionXPath,
            String requiredFieldMessage,
            String errorToastMessage,
            String personalNumberLengthMessage,
            String underageErrorToastMessage
    ) {
        this.label = label;
        this.optionXPath = optionXPath;
        this.requiredFieldMessage = requiredFieldMessage;
        this.errorToastMessage = errorToastMessage;
        this.personalNumberLengthMessage = personalNumberLengthMessage;
        this.underageErrorToastMessage = underageErrorToastMessage;
    }

    public String label() {
        return label;
    }

    public String optionXPath() {
        return optionXPath;
    }

    public String requiredFieldMessage() {
        return requiredFieldMessage;
    }

    public String errorToastMessage() {
        return errorToastMessage;
    }

    public String personalNumberLengthMessage() {
        return personalNumberLengthMessage;
    }

    public String underageErrorToastMessage() {
        return underageErrorToastMessage;
    }

    @Override
    public String toString() {
        return label;
    }
}
