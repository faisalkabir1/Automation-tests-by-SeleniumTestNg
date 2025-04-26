package testrunner;

import config.Setup;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTestRunner extends Setup {
    @Test
    public void adminLogin(){
        LoginPage loginpage = new LoginPage(driver);
        loginpage.doLogin("admin@test.com", "admin123");

    }

}
