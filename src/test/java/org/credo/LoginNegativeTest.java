package org.credo;

import Data.DataSets;
import Data.Language;
import Data.RandomTestData;
import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Epic("Authentication")
@Feature("Authorization")
public class LoginNegativeTest extends BaseTest {

    @Test(dataProvider = "languages", dataProviderClass = DataSets.class,
            description = "Login with empty username must fail")
    @Description("Negative: submit login with blank username in each supported language")
    @Story("Required username validation")
    @Severity(SeverityLevel.CRITICAL)
    public void loginWithEmptyUsername(Language language) {
        prepareLoginPage(language);
        loginSteps.login("", RandomTestData.VALID_PASSWORD);

        loginSteps.assertLoginRejected(language, "empty username")
                .assertUsernameRequired(language);
    }

    @Test(dataProvider = "languages", dataProviderClass = DataSets.class,
            description = "Login with empty password must fail")
    @Description("Negative: submit login with blank password in each supported language")
    @Story("Required password validation")
    @Severity(SeverityLevel.CRITICAL)
    public void loginWithEmptyPassword(Language language) {
        prepareLoginPage(language);
        loginSteps.login(RandomTestData.VALID_USERNAME, "");

        loginSteps.assertLoginRejected(language, "empty password")
                .assertPasswordRequired(language);
    }

    @Test(dataProvider = "languages", dataProviderClass = DataSets.class,
            description = "Login with wrong credentials must fail")
    @Description("Negative: invalid username/password pair in each supported language")
    @Story("Invalid credentials validation")
    @Severity(SeverityLevel.CRITICAL)
    public void loginWithInvalidCredentials(Language language) {
        prepareLoginPage(language);
        loginSteps.login(RandomTestData.randomInvalidUsername(), RandomTestData.randomInvalidPassword());

        loginSteps.assertLoginRejected(language, "invalid credentials")
                .assertErrorToast(language);
    }

    @Test(dataProvider = "languages", dataProviderClass = DataSets.class,
            description = "Login with random malformed username must fail")
    @Description("Negative: username generated via RandomStringUtils without valid format")
    @Story("Malformed username validation")
    @Severity(SeverityLevel.NORMAL)
    public void loginWithRandomMalformedUsername(Language language) {
        prepareLoginPage(language);
        loginSteps.login(RandomTestData.randomMalformedUsername(), RandomTestData.VALID_PASSWORD);

        loginSteps.assertLoginRejected(language, "malformed username")
                .assertErrorToast(language);
    }

    @Test(dataProvider = "languages", dataProviderClass = DataSets.class,
            description = "Login with valid username and random wrong password must fail")
    @Description("Negative: correct-looking username with RandomStringUtils password")
    @Story("Incorrect password validation")
    @Severity(SeverityLevel.CRITICAL)
    public void loginWithValidUsernameAndRandomPassword(Language language) {
        prepareLoginPage(language);
        loginSteps.login(RandomTestData.VALID_USERNAME, RandomTestData.randomWrongPassword());

        loginSteps.assertLoginRejected(language, "wrong password")
                .assertErrorToast(language);
    }

    @Step("Prepare login page in language {language}")
    private void prepareLoginPage(Language language) {
        loginSteps.selectLanguage(language);
    }

}
