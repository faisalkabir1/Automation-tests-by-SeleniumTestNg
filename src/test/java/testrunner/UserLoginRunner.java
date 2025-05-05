package testrunner;

import config.Setup;
import pages.AddCostPage;
import pages.UserLogin;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserLoginRunner extends Setup {
    UserLogin userLogin;
    public static Properties props;

    @Test(priority = 1, description = "User can Login With New Password")
    public void userLogin() throws IOException, InterruptedException {

        userLogin = new UserLogin(driver);
        props = new Properties();
        FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
        props.load(file);
        String password= props.getProperty("Password");
        String email = "fkkabir70@gmail.com";
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        userLogin.doLogin(email, password);
        Thread.sleep(2000);

    }

    @Test(priority = 2,dataProvider = "AddCostData", dataProviderClass = Config.AddCostDataSet.class, description="User Can Add Item" )
    public void addCost(String itemName, String amount, String quantity, String purchaseDate, String
            month, String remarks) throws InterruptedException {


        System.out.println("Item Name: " + itemName);
        System.out.println("Amount: " + amount);
        System.out.println("Quantity: " + quantity);
        System.out.println("Purchase Date: " + purchaseDate);
        System.out.println("Month: " + month);
        System.out.println("Remarks: " + remarks);


        AddCostPage addCostPage = new AddCostPage(driver);
        addCostPage.addExpenditure(itemName, amount, quantity, purchaseDate, month, remarks);


        Thread.sleep(2000);
        driver.switchTo().alert().accept();


    }
    @Test(dependsOnMethods = {"addCost"}, description = "Assert added items")
    public void ItemAssert() throws InterruptedException {
        Thread.sleep(5000);
        // Get the text from the rows
        String firstItem = driver.findElements(By.tagName("tr")).get(1).getText();
        String secondItem = driver.findElements(By.tagName("tr")).get(2).getText();
        System.out.println("First Item: " + firstItem);
        System.out.println("Second Item: " + secondItem );


        String expectedFirstItem = "Orange";
        String expectedSecondItem = "Fish";


        String actualFirstItem = firstItem.split(" ")[0];
        String actualSecondItem = secondItem.split(" ")[0];

        Thread.sleep(2000);

        Assert.assertEquals(actualFirstItem, expectedFirstItem, "First item is not 'Orange'");
        Assert.assertEquals(actualSecondItem, expectedSecondItem, "Second item is not 'Fish'");



    }


    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }


}
