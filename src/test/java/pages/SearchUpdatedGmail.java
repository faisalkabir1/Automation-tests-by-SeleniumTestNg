package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class SearchUpdatedGmail {


    @FindBy(tagName="tbody")
    WebElement table;
    @FindBy(className = "search-box")
    WebElement searchBox;


    public SearchUpdatedGmail(WebDriver driver){

        PageFactory.initElements(driver,this);
    }

    public void EmailSearching(String email){
        String UpdatedEmail;
        searchBox.sendKeys(email);
        WebElement firstRow = table.findElements(By.tagName("tr")).get(0);
        List<WebElement> rows = firstRow .findElements(By.tagName("td"));
        UpdatedEmail = rows .get(2).getText();
        Assert.assertEquals( UpdatedEmail,email);




    }
}