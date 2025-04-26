import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegistrationTestRunner extends Setup{

    @Test(description = "User registration")
    public void userRegistration() throws InterruptedException {
        driver.findElement(By.partialLinkText("Register")).click();
        UserRegistrationPage user=new UserRegistrationPage(driver);
        Faker faker=new Faker();
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        String email=(firstName+lastName+ Utils.generateRandomNumber(1000,9999)+"@gmail.com");
        String password="1234";
        String phoneNumber="0160"+ Utils.generateRandomNumber(1000000,9999999);
        String address=faker.address().fullAddress();
        user.userRegistration(firstName,lastName,email,password,phoneNumber,address);

        Thread.sleep(2000);
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(50)); //explicit wait
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify__toast")));
        String successfulMessageActual= driver.findElement(By.className("Toastify__toast")).getText();
        String successfulMessageExpected="registered successfully!";
        System.out.println(successfulMessageActual);
        Assert.assertTrue(successfulMessageActual.contains(successfulMessageExpected));
    }
}
