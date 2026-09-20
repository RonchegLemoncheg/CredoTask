package base;

import Steps.LoginSteps;
import Utils.BrowserConfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public abstract class BaseTest extends BrowserConfig {

    protected LoginSteps loginSteps;
    protected SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void initTestContext() {
        softAssert = new SoftAssert();
        loginSteps = new LoginSteps(driver, softAssert);
    }

    @AfterMethod(alwaysRun = true)
    public void flushSoftAssertions() {
        if (softAssert != null) {
            softAssert.assertAll();
        }
    }
}
