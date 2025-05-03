package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.Utils;

import java.io.IOException;
import java.util.List;

public class ResetPasswordPage {

    public WebDriver driver;

    @FindBy(css = "a[href='/forgot-password']")
    public WebElement linkResetPassword;
    @FindBy(tagName = "input")
    public List<WebElement> txtEmail;
    @FindBy(css = "button[type='submit']")
    public WebElement btnSendReset;
    @FindBy(tagName = "p")
    public WebElement txtInformation;
    @FindBy(tagName = "input")
    public List<WebElement> txtPassField;
    @FindBy(tagName = "button")
    public WebElement btnResetPass;

    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Correctly initializes elements
    }


    public String getValidEmail(String userEmail) throws IOException {
        txtEmail.get(0).clear();
        txtEmail.get(0).sendKeys(userEmail);
        btnSendReset.click();
        txtInformation.isDisplayed();

        String email = Utils.readLatestMail();
        String resetPassLink = email.split(": ")[1].trim();
        driver.get(resetPassLink);
        String newPass = "6666";
        txtPassField.get(0).sendKeys(newPass); // new password
        txtPassField.get(1).sendKeys(newPass);//confirm password
        btnResetPass.click();
        String MsgActual = txtInformation.getText();
        String MsgExpected = "Password reset successfully";
        System.out.println(MsgActual);
        Assert.assertTrue(MsgActual.contains(MsgExpected));

        return newPass;
    }



    public void UnregisteredEmail(String userEmail) {
        txtEmail.get(0).clear();
        txtEmail.get(0).sendKeys(userEmail);
        btnSendReset.click();
        String ActualMessage = txtInformation.getText();
        String ExpectedMessage = "Your email is not registered";
        Assert.assertTrue(ActualMessage.contains(ExpectedMessage));


    }

    public void InvalidEmail(String userEmail){
        txtEmail.get(0).clear();
        txtEmail.get(0).sendKeys(userEmail);
        btnSendReset.click();
    }

}
