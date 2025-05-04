package testrunner;

import config.Setup;
import org.testng.annotations.Test;
import pages.AdminLoginPage;

public class AdminLoginTestRunner extends Setup {

    @Test
    public void adminLogin(){
        AdminLoginPage loginpage = new AdminLoginPage(driver);
        loginpage.doLogin("admin@test.com", "admin123");

    }

}
