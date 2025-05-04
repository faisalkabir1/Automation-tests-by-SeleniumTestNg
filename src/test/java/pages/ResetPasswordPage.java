package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

    @FindBy(css = "input[type='email']")
    public WebElement txtEmail;

    @FindBy(css = "button[type='submit']")
    public WebElement btnSendReset;

    @FindBy(tagName = "p")
    public WebElement txtInformation;

    @FindBy(tagName = "input")
    public List<WebElement> txtNewPassword;


    @FindBy(css = "button[type='submit']")
    public WebElement btnResetPass;

    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void reliableClear(WebElement element) {
        try {
            // Click to focus
            element.click();

            // Select all + backspace (Ctrl+A then Delete)
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            element.sendKeys(Keys.DELETE);

            // Wait a moment to let frontend process
            Thread.sleep(200);

            // Extra JS fallback clear
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", element);

            // Optional: verify it’s empty
            String val = element.getAttribute("value");
            if (!val.isEmpty()) {
                throw new RuntimeException("Input field not cleared. Value: " + val);
            }
        } catch (Exception e) {
            System.out.println("Failed to clear field properly: " + e.getMessage());
        }
    }

    public String getValidEmail(String userEmail) throws IOException, InterruptedException {
        reliableClear(txtEmail);
        txtEmail.sendKeys(userEmail);
        btnSendReset.click();
        txtInformation.isDisplayed();

        String email = Utils.readLatestMail();
        String resetPassLink = email.split(": ")[1].trim();
        driver.get(resetPassLink);

        Utils.generateAndStorePassword(); // Generates and stores password
        String newPass = Utils.generatedPassword;
        txtNewPassword.get(0).sendKeys(newPass);
        txtNewPassword.get(1).sendKeys(newPass);
        btnResetPass.click();
        Thread.sleep(2000);
        String MsgActual = txtInformation.getText();
        String MsgExpected = "Password reset successfully";
        Assert.assertTrue(MsgActual.contains(MsgExpected));

        return newPass;
    }

    public void UnregisteredEmail(String userEmail) {
        reliableClear(txtEmail);
        txtEmail.sendKeys(userEmail);
        btnSendReset.click();

        String ActualMessage = txtInformation.getText();
        String ExpectedMessage = "Your email is not registered";
        Assert.assertTrue(ActualMessage.contains(ExpectedMessage));
    }

    public void InvalidEmail(String userEmail) {
        reliableClear(txtEmail);
        txtEmail.sendKeys(userEmail);
        btnSendReset.click();
    }
}
