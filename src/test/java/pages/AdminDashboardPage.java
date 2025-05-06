package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AdminDashboardPage {
    WebDriver driver;

    @FindBy(xpath = "//table/tbody/tr")
    List<WebElement> userRows;

    public AdminDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void exportUserDataToTextFile(String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        for (WebElement row : userRows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            if (columns.size() > 0) { // ensure it's not an empty row
                for (WebElement col : columns) {
                    writer.write(col.getText() + "\t");
                }
                writer.newLine();
            }
        }

        writer.close();
        System.out.println("User data exported to " + filePath);
    }
}
