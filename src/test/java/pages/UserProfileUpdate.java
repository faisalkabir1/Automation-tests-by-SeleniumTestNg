package pages;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class UserProfileUpdate {

    WebDriver driver;
    public static String updatedMail;
    @FindBy(css = "[data-testid=AccountCircleIcon]")
    public WebElement btnProfileIcon;

    @FindBy(css = "[role=menuitem]")
    public List<WebElement> btnProfileMenuItems;
    @FindBy (css = "[type = button]")
    public List<WebElement> btnEditUpload;


    @FindBy(css = "[type = email]")
    public WebElement txtEmail;


    public UserProfileUpdate (WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }


    public String updateEmail() throws InterruptedException{
        btnEditUpload.get(1).click();//edit

        txtEmail.sendKeys(Keys.CONTROL,"a");
        txtEmail.sendKeys(Keys.BACK_SPACE);

        updatedMail = "onlyfaisalkabir" + (int) (Math.random() * 1000) + "@gmail.com";
        System.out.println(updatedMail);
        txtEmail.sendKeys(updatedMail);
        btnEditUpload.get(2).click();//upload

        Thread.sleep(5000);
//       driver.switchTo().alert().accept();


        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String userUpdateMsgExpected = "User updated successfully!";
        String userUpdateMsgActual = alert.getText();
        Assert.assertTrue(userUpdateMsgActual.contains(userUpdateMsgExpected));
        alert.accept();
        return updatedMail;

    }










}
