package config;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDataProvider {

    @DataProvider(name = "UserCSVData")
    public Object[][] getCSVData() throws IOException {
        String filePath = "./src/test/resources/users.csv";
        List<Object[]> data = new ArrayList<>();

        CSVParser csvParser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withFirstRecordAsHeader());

        for (CSVRecord csvRecord : csvParser) {
            String firstname = csvRecord.get("firstname");
            String lastname = csvRecord.get("lastname");
            String email = csvRecord.get("email");
            String password = csvRecord.get("password");
            String phonenumber = csvRecord.get("phonenumber");
            String address = csvRecord.get("address");

            data.add(new Object[]{firstname, lastname, email, password, phonenumber, address});
        }

        return data.toArray(new Object[0][0]);
    }
}