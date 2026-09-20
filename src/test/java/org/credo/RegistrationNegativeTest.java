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
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import java.time.LocalDate;

@Epic("Customer Onboarding")
@Feature("Registration")
public class RegistrationNegativeTest extends BaseTest {

    @Test(dataProvider = "languages", dataProviderClass = DataSets.class,
            description = "Registration with empty personal number must fail")
    @Description("Negative: submit registration with a blank personal number in each supported language")
    @Story("Required personal number validation")
    @Severity(SeverityLevel.CRITICAL)
    public void registrationWithEmptyPersonalNumber(Language language) {
        loginSteps.selectLanguage(language);
        loginSteps.goToRegistration()
                .submitEmptyForm()
                .assertRegistrationPageRemainsOpen()
                .assertPersonalNumberRequired(language);
    }

    @Test(dataProvider = "languages", dataProviderClass = DataSets.class,
            description = "Registration with a 10-digit personal number must fail")
    @Description("Negative: submit registration with a personal number shorter than 11 digits")
    @Story("Personal number minimum length validation")
    @Severity(SeverityLevel.NORMAL)
    public void registrationWithTenDigitPersonalNumber(Language language) {
        loginSteps.selectLanguage(language);
        loginSteps.goToRegistration()
                .enterPersonalNumber(RandomTestData.randomPersonalNumber(10))
                .submitForm()
                .assertRegistrationPageRemainsOpen()
                .assertPersonalNumberLength(language);
    }

    @Test(dataProvider = "languages", dataProviderClass = DataSets.class,
            description = "Registration with a 12-digit personal number must fail")
    @Description("Negative: submit registration with a personal number longer than 11 digits")
    @Story("Personal number maximum length validation")
    @Severity(SeverityLevel.NORMAL)
    public void registrationWithTwelveDigitPersonalNumber(Language language) {
        loginSteps.selectLanguage(language);
        loginSteps.goToRegistration()
                .enterPersonalNumber(RandomTestData.randomPersonalNumber(12))
                .submitForm()
                .assertRegistrationPageRemainsOpen()
                .assertPersonalNumberLength(language);
    }

    @Test(dataProvider = "languages", dataProviderClass = DataSets.class,
            description = "Registration with valid personal number must request birth date")
    @Description("Negative: submit registration without completing the birth date")
    @Story("Required birth date validation")
    @Severity(SeverityLevel.CRITICAL)
    public void registrationWithMissingBirthDate(Language language) {
        loginSteps.selectLanguage(language);
        loginSteps.goToRegistration()
                .enterPersonalNumber(RandomTestData.randomPersonalNumber(11))
                .submitForm()
                .assertBirthDateFieldsDisplayed()
                .assertBirthDateRequired(language);
    }

    @Test(dataProvider = "languages", dataProviderClass = DataSets.class,
            description = "Registration for a person who is not yet 18 must fail")
    @Description("Negative: submit an otherwise valid registration for a person turning 18 tomorrow")
    @Story("Legal age validation")
    @Severity(SeverityLevel.CRITICAL)
    public void registrationWithUnderagePerson(Language language) {
        LocalDate underageBirthDate = LocalDate.now().minusYears(18).plusDays(1);

        loginSteps.selectLanguage(language);
        loginSteps.goToRegistration()
                .enterPersonalNumber(RandomTestData.randomPersonalNumber(11))
                .submitForm()
                .assertBirthDateFieldsDisplayed()
                .chooseBirthDate(underageBirthDate)
                .submitForm()
                .assertErrorToast(language);
    }
}
