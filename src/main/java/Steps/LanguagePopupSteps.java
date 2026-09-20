package Steps;

import Data.Language;
import Page.LanguagePopup;
import Page.LoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class LanguagePopupSteps extends BaseSteps {

    private final LanguagePopup popup;

    public LanguagePopupSteps(WebDriver driver, SoftAssert softAssert) {
        super(driver, softAssert);
        this.popup = new LanguagePopup(driver);
    }

    @Step("Open language selector")
    public LanguagePopupSteps open(LoginPage loginPage) {
        wait.waitForClickable(loginPage.languagePopupButton).click();
        wait.waitUntilVisible(popup.popupRoot);
        return this;
    }

    @Step("Select language: {language}")
    public void selectLanguage(Language language) {
        WebElement option = wait.waitForVisible(LanguagePopup.optionFor(language));
        wait.waitForClickable(option).click();
        wait.waitForInvisible(popup.popupRoot);
    }
}
