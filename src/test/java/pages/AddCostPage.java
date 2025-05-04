package pages;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AddCostPage {


    JavascriptExecutor js;

    WebDriver driver;

    @FindBy(className = "add-cost-button")
    public WebElement btnAddCost;

    @FindBy(id ="itemName")
    WebElement txtItemName;

    @FindBy(id="amount")
    WebElement txtAmount;
    @FindBy(id = "purchaseDate")
    WebElement datePicker;

    @FindBy(css = "button[type='button']")
    public List<WebElement>   btnQuantity;
    @FindBy(id = "remarks")
    WebElement txtRemark;
    @FindBy(id = "month")
    WebElement selectMonths;
    @FindBy(css = "[type=submit]")
    WebElement btnSubmit;



    public AddCostPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }


    public void addExpenditure(String itemName, String amount, String quantity, String purchaseDate, String month, String remarks) throws InterruptedException {
        Thread.sleep(2000);
        btnAddCost.click(); // Click the button to add cost
        txtItemName.sendKeys(itemName);
        txtAmount.sendKeys(amount);
        btnQuantity.get(2).click();
        btnQuantity.get(2).sendKeys(quantity);

        datePicker.clear();
        datePicker.sendKeys(purchaseDate);

        Select monthSelect = new Select(selectMonths);
        monthSelect.selectByValue(month);
        txtRemark.sendKeys(remarks);



        btnSubmit.click();


    }

}