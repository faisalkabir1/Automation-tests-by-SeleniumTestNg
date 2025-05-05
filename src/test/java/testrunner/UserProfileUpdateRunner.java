package testrunner;

import config.Setup;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.UserLogin;
import pages.UserProfileUpdate;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static utils.Utils.props;

public class UserProfileUpdateRunner extends Setup {


    UserLogin userLogin;
    UserProfileUpdate userProfileUpdate;
    String updatedEmail;
    @Test(priority = 1, description = "Update Gmail from profile")
    public void updateUserEmail() throws InterruptedException, IOException {
        userLogin = new UserLogin(driver);
        props = new Properties();
        FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
        props.load(file);
        String password= props.getProperty("Password");
        String email = "fkkabir70@gmail.com";
        userLogin.doLogin(email, password);
        Thread.sleep(5000);
        userProfileUpdate = new UserProfileUpdate(driver);
        userProfileUpdate.btnProfileIcon.click();
        userProfileUpdate.btnProfileMenuItems.get(0).click();
        userProfileUpdate.updateEmail();
        userLogin.doLogout();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable( userLogin.btnsubmit));


    }
    @Test(priority = 2, description = "User can not login with old email")
    public void userLoginOld() throws IOException, InterruptedException {
        userLogin = new  UserLogin(driver);
        userLogin.btnsubmit.click();
        Thread.sleep(2000);
        props = new Properties();
        FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
        props.load(file);
        String password = props.getProperty("Password");
        userProfileUpdate = new UserProfileUpdate(driver);
        String oldEmail = "fkkabir70@gmail.com";
        userLogin.doLogin(oldEmail,password);
        Thread.sleep(2000);
        String expectedMsg = "Invalid email or password";
        String actualMsg = userLogin.errorMsg.get(0).getText();
        Assert.assertTrue(actualMsg.contains(expectedMsg));
    }

    @Test(priority = 3, description = "User Login with updated email")
    public void userLoginNew() throws IOException, InterruptedException {
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
        System.out.println(actualMsg);
        Assert.assertTrue(actualMsg.contains(expectedMsg));
    }


    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }





}