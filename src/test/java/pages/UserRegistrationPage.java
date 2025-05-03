package pages;

import config.UserModel;
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

    public void userRegistration(UserModel userModel){
        txtInput.get(0).sendKeys(userModel.getFirstname()); //firstname
        txtInput.get(1).sendKeys(userModel.getLastname()); //lastname
        txtInput.get(2).sendKeys(userModel.getEmail());
        txtInput.get(3).sendKeys(userModel.getPassword()); //password
        txtInput.get(4).sendKeys(userModel.getPhonenumber()); //phoneNumber'
        txtInput.get(5).sendKeys(userModel.getAddress()); //Address
        txtInput.get(6).click(); //select male gender
        txtInput.get(8).click(); //accept terms
        btnRegister.click();
    }
}
