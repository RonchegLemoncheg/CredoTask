package Steps;

import Data.Language;
import Page.LoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class LoginSteps extends BaseSteps {

    private final LoginPage page;
    private final LanguagePopupSteps languagePopupSteps;
    private final RegistrationSteps registrationSteps;

    public LoginSteps(WebDriver driver, SoftAssert softAssert) {
        super(driver, softAssert);
        this.page = new LoginPage(driver);
        this.languagePopupSteps = new LanguagePopupSteps(driver, softAssert);
        this.registrationSteps = new RegistrationSteps(driver, softAssert);
    }

    @Step("Switch UI language to {language}")
    public LoginSteps selectLanguage(Language language) {
        languagePopupSteps.open(page).selectLanguage(language);
        return this;
    }

    @Step("Enter username")
    public LoginSteps enterUsername(String username) {
        WebElement field = wait.waitUntilVisible(page.usernameInput);
        field.clear();
        if (username != null && !username.isEmpty()) {
            field.sendKeys(username);
        }
        return this;
    }

    @Step("Enter password")
    public LoginSteps enterPassword(String password) {
        WebElement field = wait.waitUntilVisible(page.passwordInput);
        field.clear();
        if (password != null && !password.isEmpty()) {
            field.sendKeys(password);
        }
        return this;
    }

    @Step("Submit login form")
    public LoginSteps submitLogin() {
        wait.waitForClickable(page.submitButton).click();
        return this;
    }

    @Step("Perform login attempt")
    public LoginSteps login(String username, String password) {
        return enterUsername(username).enterPassword(password).submitLogin();
    }

    @Step("Assert login page remains open after {scenario} in {language}")
    public LoginSteps assertLoginRejected(Language language, String scenario) {
        softAssert.assertTrue(
                isOnLoginPage(),
                "User should remain on login page after " + scenario + " [" + language + "]"
        );
        return this;
    }

    @Step("Assert username required message in {language}")
    public LoginSteps assertUsernameRequired(Language language) {
        softAssert.assertEquals(
                getUsernameFieldErrorText(),
                language.requiredFieldMessage(),
                "Unexpected username validation message for " + language
        );
        return this;
    }

    @Step("Assert password required message in {language}")
    public LoginSteps assertPasswordRequired(Language language) {
        softAssert.assertEquals(
                getPasswordFieldErrorText(),
                language.requiredFieldMessage(),
                "Unexpected password validation message for " + language
        );
        return this;
    }

    @Step("Assert localized login error toast in {language}")
    public LoginSteps assertErrorToast(Language language) {
        softAssert.assertEquals(
                getErrorMessageText(),
                language.errorToastMessage(),
                "Unexpected global error message for " + language
        );
        return this;
    }

    @Step("Read global error message")
    public String getErrorMessageText() {
        wait.fluentWaitUntil(d -> {
            try {
                return page.errorToast.isDisplayed() && !page.errorToast.getText().isBlank();
            } catch (Exception e) {
                return false;
            }
        });
        return page.errorToast.getText().trim();
    }

    @Step("Read username field validation message")
    public String getUsernameFieldErrorText() {
        return wait.waitUntilVisible(page.usernameError).getText().trim();
    }

    @Step("Read password field validation message")
    public String getPasswordFieldErrorText() {
        return wait.waitUntilVisible(page.passwordError).getText().trim();
    }

    @Step("Check user remains on login page")
    public boolean isOnLoginPage() {
        try {
            return page.usernameInput.isDisplayed() && page.passwordInput.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Go to Registration page")
    public RegistrationSteps goToRegistration() {
        wait.waitForClickable(page.registrationLink).click();
        registrationSteps.waitUntilOpened();
        return this.registrationSteps;
    }
}
