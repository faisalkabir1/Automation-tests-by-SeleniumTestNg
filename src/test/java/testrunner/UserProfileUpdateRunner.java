package testrunner;

import config.Setup;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.UserLogin;
import pages.UserProfileUpdate;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.util.Properties;

import static utils.Utils.props;

public class UserProfileUpdateRunner extends Setup {


    UserLogin userLogin;
    UserProfileUpdate userProfileUpdate;
    String updatedEmail;
    @Test(priority = 1, description = "Update Gmail")
    public void updateUserEmail() throws InterruptedException, IOException, ParseException {

        userProfileUpdate = new UserProfileUpdate(driver);
        userProfileUpdate.btnProfileIcon.click();
        userProfileUpdate.btnProfileMenuItems.get(0).click();
        userProfileUpdate.updateEmail();
        userLogin.doLogout();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable( userLogin.btnsubmit));


    }

    @Test(priority = 2, description = "User Login for update email")
    public void userLogin() throws IOException, InterruptedException {
        Thread.sleep(5000);
        userLogin = new  UserLogin(driver);
        userLogin.btnsubmit.click();
        Thread.sleep(2000);
        props = new Properties();
        FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
        props.load(file);
        String password = props.getProperty("Password");
        userProfileUpdate = new UserProfileUpdate(driver);
        String newEmail = UserProfileUpdate.updatedMail;
        userLogin.doLogin(newEmail,password);
        Thread.sleep(2000);
        String expectedMsg = "User Daily Costs";
        String actualMsg = userLogin.dashboardMsg.getText();
        Assert.assertTrue(actualMsg.contains(expectedMsg));
    }


    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }





}