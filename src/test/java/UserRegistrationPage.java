import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserRegistrationPage {
    @FindBy(tagName="input")
    List<WebElement> txtInput;
    @FindBy(id="register")
    WebElement btnRegister;
    public UserRegistrationPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    public void userRegistration(String firstname, String lastname, String email, String password, String phoneNumber, String address){
        txtInput.get(0).sendKeys(firstname); //firstname
        txtInput.get(1).sendKeys(lastname); //lastname
        txtInput.get(2).sendKeys(email);
        txtInput.get(3).sendKeys(password); //password
        txtInput.get(4).sendKeys(phoneNumber); //phoneNumber'
        txtInput.get(5).sendKeys(address);
        txtInput.get(6).click(); //select male gender
        txtInput.get(8).click(); //accept terms
        btnRegister.click();
    }
}
