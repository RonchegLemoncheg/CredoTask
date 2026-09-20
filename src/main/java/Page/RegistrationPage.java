package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='personalNumber']")
    public WebElement personalNumberInput;

    @FindBy(xpath = "//crd-date-dropdowns")
    public WebElement birthDateDropdowns;

    @FindBy(xpath = "//crd-new-select[position()=1]")
    public WebElement dayInput;

    @FindBy(xpath = "//crd-new-select[position()=2]")
    public WebElement monthInput;

    @FindBy(xpath = "//crd-new-select[position()=3]")
    public WebElement yearInput;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitButton;

    @FindBy(xpath = "//form-field[.//input[@id='personalNumber']]//crd-error")
    public WebElement personalNumberError;

    @FindBy(xpath = "//crd-date-dropdowns/following-sibling::p")
    public WebElement birthDateError;

    @FindBy(xpath = "//div[@role='alert']")
    public WebElement errorToast;

}