package Page;

import Data.Language;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LanguagePopup extends BasePage {

    public LanguagePopup(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(@class, 'rounded-xl')]")
    public WebElement popupRoot;

    public static By optionFor(Language language) {
        return By.xpath(language.optionXPath());
    }
}
