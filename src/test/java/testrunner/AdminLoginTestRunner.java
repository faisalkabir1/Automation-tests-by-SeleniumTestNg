package testrunner;

import config.Setup;
import org.apache.commons.configuration.plist.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.AdminLoginPage;
import pages.SearchUpdatedGmail;
import pages.UserProfileUpdate;

import java.io.IOException;
import java.time.Duration;

public class AdminLoginTestRunner extends Setup {

////    @Test
////    public void adminLogin(){
////        AdminLoginPage loginpage = new AdminLoginPage(driver);
////        loginpage.doLogin("admin@test.com", "admin123");
//
//    }

private AdminLoginPage adminLoginPage;
    private SearchUpdatedGmail searchUpdatedGmail;
    // private WebDriver driver;
    @Test(priority = 1, description = "Admin Login")
    public void adminLogin() throws IOException {

        AdminLoginPage adminLoginPage = new   AdminLoginPage(driver);
        if(System.getProperty("username")!=null && System.getProperty("password")!=null){
            adminLoginPage.doLogin(System.getProperty("username"),System.getProperty("password"));
        }
        else{
            adminLoginPage.doLogin("admin@test.com","admin123");
        }
        // Wait for the header element to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement headerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h2")));
        String headerActual = headerElement.getText();
        String headerExpected = "Admin Dashboard";

        Assert.assertTrue(headerActual.contains(headerExpected));

    }

    @Test(priority = 2, description = "Searching updated gmail in row")
    public void searchUpdatedGmail() throws InterruptedException {
        String email= "onlyfaisalkabir@gmail.com";
        searchUpdatedGmail = new SearchUpdatedGmail(driver);
        Thread.sleep(2000);
        searchUpdatedGmail.EmailSearching(email);
        Thread.sleep(2000);
        AdminLoginPage.doLogout();

    }


    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }

}
