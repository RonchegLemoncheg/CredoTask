package Utils;

import Data.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

/**
 * Builds and tears down Chrome sessions. Call {@link #prepareChromeDriver()} once per suite;
 * each test method should use {@link #openChrome()} / {@link #close(WebDriver)}.
 */
public final class DriverManager {

    private DriverManager() {
    }

    public static void prepareChromeDriver() {
        WebDriverManager.chromedriver().setup();
    }

    public static WebDriver openChrome() {
        ChromeDriver driver = new ChromeDriver(buildChromeOptions());
        applyTimeouts(driver);
        driver.manage().window().maximize();
        return driver;
    }

    public static void close(WebDriver driver) {
        if (driver == null) {
            return;
        }
        try {
            driver.quit();
        } catch (RuntimeException ignored) {
        }
    }

    private static void applyTimeouts(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT);
    }

    private static ChromeOptions buildChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-search-engine-choice-screen");
        if (Constants.HEADLESS) {
            options.addArguments("--headless=new", "--window-size=1920,1080");
        }
        return options;
    }
}
