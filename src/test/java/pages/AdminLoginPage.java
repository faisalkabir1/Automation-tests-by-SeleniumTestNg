package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminLoginPage {
    @FindBy(id="email")
    WebElement txtEmail;
    @FindBy(id="password")
    WebElement txtPassword;
    @FindBy(tagName="button")
    WebElement btnLogin;

    public AdminLoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);

    }
    public void doLogin(String email, String password){
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }

}
