package Utils;

import Data.Constants;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

/**
 * Base TestNG configuration: one fresh browser session per test method, opened on the auth URL.
 */
public abstract class BrowserConfig {

    protected WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void prepareWebDriver() {
        DriverManager.prepareChromeDriver();
    }

    @BeforeMethod(alwaysRun = true)
    public void beginSession() {
        if (driver != null) {
            DriverManager.close(driver);
            driver = null;
        }

        driver = DriverManager.openChrome();

        try {
            driver.get(Constants.CREDO_URL);
        } catch (RuntimeException firstFailure) {
            driver.get(Constants.CREDO_URL);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void endSession() {
        if (driver != null) {
            DriverManager.close(driver);
        }
        driver = null;
    }
}
