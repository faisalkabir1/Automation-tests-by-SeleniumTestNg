package testrunner;

import config.Setup;
import org.testng.annotations.Test;
import pages.AdminDashboardPage;
import pages.AdminLoginPage;

public class UserTableDataScrapTest extends Setup {

    @Test(description = "Login as admin and export users to file")
    public void exportUsersToFile() throws Exception {
        driver.get("https://dailyfinance.roadtocareer.net/");

        AdminLoginPage loginPage = new AdminLoginPage(driver);
        loginPage.doLogin("admin@test.com", "admin123");
        Thread.sleep(5000);
        AdminDashboardPage dashboard = new AdminDashboardPage(driver);
        dashboard.exportUserDataToTextFile("./src/test/resources/users-data.txt");
    }
}