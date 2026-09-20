package Steps;

import Data.Language;
import Page.RegistrationPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;

public class RegistrationSteps extends BaseSteps {

    final RegistrationPage page;

    public RegistrationSteps(WebDriver driver, SoftAssert softAssert) {
        super(driver, softAssert);
        this.page = new RegistrationPage(driver);
    }

    @Step("Wait for registration form")
    public RegistrationSteps waitUntilOpened() {
        wait.waitUntilVisible(page.personalNumberInput);
        return this;
    }

    @Step("Enter personal number '{personalNumber}'")
    public RegistrationSteps enterPersonalNumber(String personalNumber) {
        WebElement field = wait.waitUntilVisible(page.personalNumberInput);
        field.clear();
        if (personalNumber != null && !personalNumber.isEmpty()) {
            field.sendKeys(personalNumber);
        }
        return this;
    }

    @Step("Choose birth day '{day}'")
    public RegistrationSteps chooseDay(int day) {
        chooseDatePart(page.dayInput, String.valueOf(day));
        return this;
    }

    @Step("Choose birth month '{month}'")
    public RegistrationSteps chooseMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12");
        }

        wait.waitForClickable(page.monthInput).click();
        By monthOption = By.xpath("(//*[@role='listitem'])[" + month + "]");
        wait.waitForClickable(wait.waitForVisible(monthOption)).click();
        return this;
    }

    @Step("Choose birth year '{year}'")
    public RegistrationSteps chooseYear(int year) {
        chooseDatePart(page.yearInput, String.valueOf(year));
        return this;
    }

    @Step("Choose birth date '{birthDate}'")
    public RegistrationSteps chooseBirthDate(LocalDate birthDate) {
        return chooseDay(birthDate.getDayOfMonth())
                .chooseMonth(birthDate.getMonthValue())
                .chooseYear(birthDate.getYear());
    }

    @Step("Submit the registration form")
    public RegistrationSteps submitForm() {
        wait.waitForClickable(page.submitButton).click();
        return this;
    }

    @Step("Submit the registration form with no personal number")
    public RegistrationSteps submitEmptyForm() {
        return enterPersonalNumber("").submitForm();
    }

    @Step("Assert personal number required message in {language}")
    public RegistrationSteps assertPersonalNumberRequired(Language language) {
        softAssert.assertEquals(
                wait.waitUntilVisible(page.personalNumberError).getText().trim(),
                language.requiredFieldMessage(),
                "Personal number should be reported as required in " + language);
        return this;
    }

    @Step("Assert personal number length message in {language}")
    public RegistrationSteps assertPersonalNumberLength(Language language) {
        softAssert.assertEquals(
                wait.waitUntilVisible(page.personalNumberError).getText().trim(),
                language.personalNumberLengthMessage(),
                "Unexpected personal number length message in " + language);
        return this;
    }

    @Step("Assert birth date required message in {language}")
    public RegistrationSteps assertBirthDateRequired(Language language) {
        softAssert.assertEquals(
                wait.waitUntilVisible(page.birthDateError).getText().trim(),
                language.requiredFieldMessage(),
                "Birth date should be reported as required in " + language);
        return this;
    }

    @Step("Assert birth date fields are displayed")
    public RegistrationSteps assertBirthDateFieldsDisplayed() {
        softAssert.assertTrue(wait.isVisible(page.birthDateDropdowns),
                "The birth date fields should be shown");
        return this;
    }

    @Step("Assert registration error toast in {language}")
    public RegistrationSteps assertErrorToast(Language language) {
        softAssert.assertEquals(
                getErrorToastText(),
                language.underageErrorToastMessage(),
                "Unexpected registration error toast in " + language);
        return this;
    }

    @Step("Assert registration form remains open")
    public RegistrationSteps assertRegistrationPageRemainsOpen() {
        softAssert.assertTrue(driver.getCurrentUrl().contains("/landing/registration/customer-check"),
                "A rejected registration must leave the browser on the customer check step");
        softAssert.assertTrue(page.personalNumberInput.isDisplayed(),
                "The personal number field should still be on screen");
        return this;
    }

    private void chooseDatePart(WebElement datePart, String value) {
        wait.waitForClickable(datePart).click();
        By option = By.xpath("//*[@role='listitem'][normalize-space()=" + quote(value) + "]");
        wait.waitForClickable(wait.waitForVisible(option)).click();
    }

    private String getErrorToastText() {
        wait.fluentWaitUntil(d -> page.errorToast.isDisplayed() && !page.errorToast.getText().isBlank());
        return page.errorToast.getText().trim();
    }

    private String quote(String value) {
        return "'" + value.replace("'", "&apos;") + "'";
    }
}