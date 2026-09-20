package Utils;

import Data.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

public class WaitUtils {

    private final WebDriver driver;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitUntilVisible(WebElement element) {
        return explicitWait().until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForVisible(By locator) {
        return explicitWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(WebElement element) {
        return explicitWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean waitForInvisible(WebElement element) {
        return explicitWait().until(ExpectedConditions.invisibilityOf(element));
    }

    public <T> T fluentWaitUntil(Function<WebDriver, T> condition) {
        return fluent().until(condition);
    }

    public boolean isVisible(WebElement element) {
        try {
            return waitUntilVisible(element) != null;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    private FluentWait<WebDriver> fluent() {
        return new FluentWait<>(driver)
                .withTimeout(Constants.FLUENT_TIMEOUT)
                .pollingEvery(Constants.POLL_INTERVAL)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    private WebDriverWait explicitWait() {
        return new WebDriverWait(driver, Constants.EXPLICIT_TIMEOUT);
    }
}
