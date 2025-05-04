package Config;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddCostDataSet {


    @DataProvider(name = "AddCostData")
    public Object[][] getCSVData() throws IOException {
        String filePath = "./src/test/resources/addCost.csv";
        List<Object[]> data = new ArrayList<>();
        CSVParser csvParser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withFirstRecordAsHeader());




        for(CSVRecord csvRecord: csvParser){

            String itemName=csvRecord.get("Item Name");
            String amount=csvRecord.get("Amount");
            String quantity =csvRecord.get("Quantity");
            String purchaseDate=csvRecord.get("Purchase Date");
            String month = csvRecord.get("Month");
            String remarks = csvRecord.get("Remarks");
            data.add(new Object[]{itemName, amount, quantity,purchaseDate,month ,remarks});

        }


        return data.toArray(new Object[0][]);
    }




}
