package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserLogin {

    @FindBy(id="email")
    public WebElement txtEmail;
    @FindBy(id="password")
    public WebElement txtPassword;
    @FindBy( css = "button[type='submit']")
    public WebElement btnsubmit;
    @FindBy(css = "[data-testid=AccountCircleIcon]")
    public WebElement btnProfileIcon;
    @FindBy(css = "[role=menuitem]")
    public List<WebElement> btnProfileMenuItems;
    @FindBy(tagName = "h2")
    public WebElement dashboardMsg;
    @FindBy(tagName = "p")
    public List<WebElement> errorMsg;

    public  UserLogin (WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void doLogin(String email, String password){
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnsubmit.click();

    }
    public void doLogout(){
        btnProfileIcon.click();
        btnProfileMenuItems.get(1).click();
    }
}
