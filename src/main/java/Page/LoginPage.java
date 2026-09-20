package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='username']")
    public WebElement usernameInput;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitButton;

    @FindBy(xpath = "//app-icon[@svgIcon='language']")
    public WebElement languagePopupButton;

    @FindBy(xpath = "//div[@role='alert']")
    public WebElement errorToast;

    @FindBy(xpath = "//form-field[.//input[@id='username']]//crd-error")
    public WebElement usernameError;

    @FindBy(xpath = "//form-field[.//input[@id='password']]//crd-error")
    public WebElement passwordError;

    @FindBy(xpath = "//a[@aria-label='registration']")
    public WebElement registrationLink;

}
