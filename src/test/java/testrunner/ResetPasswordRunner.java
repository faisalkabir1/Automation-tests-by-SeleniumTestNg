package testrunner;

import config.Setup;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.ResetPasswordPage;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class ResetPasswordRunner extends Setup {

    public String newPass;
    public String userEmail;
    ResetPasswordPage resetPass;

    @BeforeClass
    public void init() {
        // Initialize the ResetPasswordPage after driver is set up
        resetPass = new ResetPasswordPage(driver);
    }


    @Test(priority = 3, description = " Give valid gmail account in Forgot Password Field")
    public void RegisteredGmail() throws IOException {
        //  driver.get("https://dailyfinance.roadtocareer.net/login");
        //resetPass.linkResetPassword.click();
        resetPass.getValidEmail("fkkabir70@gmail.com");

        newPass = resetPass.getValidEmail(userEmail);

    }


    //Negative Case

    @Test(priority = 1, description = "Reset New Password With Unregistered Email")
    public void resetPassUnregisteredEmail() {
      //  driver.get("https://dailyfinance.roadtocareer.net/login");
        resetPass.linkResetPassword.click();
        resetPass.UnregisteredEmail("abcd1234@gmail.com");


    }


    //Negative Case

    @Test(priority = 2, description = "Reset New Password With Invalid Email")
    public void resetPassInvalidEmail() {
      //  driver.get("https://dailyfinance.roadtocareer.net/login");
//        resetPass.linkResetPassword.click();
        resetPass.InvalidEmail("abcd1234@@gmail..com");
        WebElement emailInput = resetPass.txtEmail.get(0);
        boolean isValid = (Boolean) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].checkValidity();", emailInput);

        Assert.assertFalse(isValid); // for invalid email
    }



    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }

}
