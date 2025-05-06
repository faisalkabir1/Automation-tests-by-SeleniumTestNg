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

        // Write headers
        List<WebElement> headerColumns = driver.findElements(By.xpath("//table/thead/tr/th"));
        for (int i = 0; i < headerColumns.size() - 1; i++) { // ignore last column (View)
            writer.write(headerColumns.get(i).getText() + "\t");
        }
        writer.newLine();

        // Write user rows
        for (WebElement row : userRows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            if (columns.size() > 1) { // ensure not empty
                for (int i = 0; i < columns.size() - 1; i++) { // skip last td (View)
                    writer.write(columns.get(i).getText() + "\t");
                }
                writer.newLine();
            }
        }
        writer.close();
        System.out.println("User data exported to " + filePath);
    }
    }
