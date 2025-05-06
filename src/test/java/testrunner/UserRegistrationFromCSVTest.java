package testrunner;

import config.Setup;
import config.UserModel;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.UserRegistrationPage;

public class UserRegistrationFromCSVTest extends Setup {

    @Test(dataProvider = "UserCSVData", dataProviderClass = config.UserDataProvider.class,
            priority = 1, description = "Register multiple users using CSV and DataProvider")
    public void registerUser(String firstname, String lastname, String email, String password,
                             String phonenumber, String address) throws InterruptedException {

        System.out.println("Registering: " + firstname + " " + lastname);

        driver.findElement(By.partialLinkText("Register")).click();

        UserModel userModel = new UserModel();
        userModel.setFirstname(firstname);
        userModel.setLastname(lastname);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phonenumber);
        userModel.setAddress(address);

        UserRegistrationPage registrationPage = new UserRegistrationPage(driver);
        registrationPage.userRegistration(userModel);

        Thread.sleep(3000); // wait for success message
    }
}
