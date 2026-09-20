package Steps;

import Utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public abstract class BaseSteps {

    protected final WebDriver driver;
    protected final WaitUtils wait;
    protected final SoftAssert softAssert;

    protected BaseSteps(WebDriver driver, SoftAssert softAssert) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        this.softAssert = softAssert;
    }
}
