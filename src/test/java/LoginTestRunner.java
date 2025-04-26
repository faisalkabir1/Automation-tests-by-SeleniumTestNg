import org.testng.annotations.Test;

public class LoginTestRunner extends Setup {
    @Test
    public void adminLogin(){
        LoginPage loginpage = new LoginPage(driver);
        loginpage.doLogin("admin@test.com", "admin123");

    }

}
