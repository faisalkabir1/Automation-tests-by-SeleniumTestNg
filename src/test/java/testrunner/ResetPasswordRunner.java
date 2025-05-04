package testrunner;

import config.Setup;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ResetPasswordPage;

import java.io.IOException;

public class ResetPasswordRunner extends Setup {

    public String newPass;
    public String userEmail;
    ResetPasswordPage resetPass;

    @BeforeClass
    public void init() {
        resetPass = new ResetPasswordPage(driver);
    }

    @Test(priority = 1, description = "Reset New Password With Unregistered Email")
    public void resetPassUnregisteredEmail() {
        resetPass.linkResetPassword.click();
        resetPass.UnregisteredEmail("abcd1234@gmail.com");
    }

    @Test(priority = 2, description = "Reset New Password With Invalid Email")
    public void resetPassInvalidEmail() {
        resetPass.InvalidEmail("abcd1234@@gmail..com");
//invalid email validation checks on browser should execute
        WebElement emailInput = resetPass.txtEmail;
        boolean isValid = (Boolean) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].checkValidity();", emailInput); //check email validation is called

        Assert.assertFalse(isValid, "Invalid email was unexpectedly accepted!");
    }

    @Test(priority = 3, description = "Give valid gmail account in Forgot Password Field and Change the password")
    public void RegisteredGmail() throws IOException, InterruptedException {
        userEmail = "fkkabir70@gmail.com";
        newPass = resetPass.getValidEmail(userEmail);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
