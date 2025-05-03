package testrunner;

import com.github.javafaker.Faker;
import config.Setup;
import config.UserModel;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.UserRegistrationPage;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

public class RegistrationTestRunner extends Setup {

    @Test(description = "User registration by providing all info")
    public void userRegistration() throws InterruptedException, IOException {
        driver.findElement(By.partialLinkText("Register")).click();
        UserRegistrationPage user = new UserRegistrationPage(driver);
        Faker faker = new Faker();
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        String email = "fkkabir70+" + (int) (Math.random() * 1000) + "@gmail.com";
        String password = "1234";
        String phonenumber = "0160" + Utils.generateRandomNumber(1000000, 9999999);
        String address = faker.address().fullAddress();
        UserModel userModel = new UserModel();
        userModel.setFirstname(firstname);
        userModel.setLastname(lastname);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phonenumber);
        userModel.setAddress(address);
        user.userRegistration(userModel);
        Thread.sleep(4000);
        doAssertion();
        assertRegistrationEmail(firstname);
    }
        public void doAssertion() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50)); //explicit wait
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify__toast")));
            String successfulMessageActual = driver.findElement(By.className("Toastify__toast")).getText();
            String successfulMessageExpected = "registered successfully!";
            System.out.println(successfulMessageActual);
            Assert.assertTrue(successfulMessageActual.contains(successfulMessageExpected));
        }
        public  void assertRegistrationEmail(String firstName) throws IOException, InterruptedException {
            Thread.sleep(4000);
            String confirmationEmailActual = Utils.readLatestMail();
            String confirmationEmailExpected = "Dear "+ firstName  + ", Welcome to our platform!";
            System.out.println(confirmationEmailActual);
            Assert.assertTrue( confirmationEmailActual.contains(confirmationEmailExpected) );

        }

    }

