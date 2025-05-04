package utils;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Utils {
    public static Properties props;
    public static FileInputStream file;
    public static String generatedPassword;

    public static int generateRandomNumber(int min, int max){
        double randomNumber= Math.random()*(max-min)+min;
        return (int)randomNumber;
    }

    public static void main(String[] args) {
        int id= generateRandomNumber(1000,9999);
     //   System.out.println(id);
    }

    public static String getEmailList() throws IOException {

        props = new Properties();
        FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
        props.load(file);

        RestAssured.baseURI = "https://gmail.googleapis.com";
        Response res = given().contentType("application/json")
                .header("Authorization", "Bearer " + props.getProperty("google_access_token"))
                .when().get("/gmail/v1/users/me/messages");



        JsonPath jsonPath = res.jsonPath();
        return jsonPath.getString("messages[0].id");

    }

    public static String readLatestMail() throws IOException {

        String messageId = getEmailList();
        RestAssured.baseURI = "https://gmail.googleapis.com";
        Response res = given().contentType("application/json")
                .header("Authorization", "Bearer " + props.getProperty("google_access_token"))
                .when().get("/gmail/v1/users/me/messages/" + messageId);

        //System.out.println(res.asString());

        JsonPath jsonPath = res.jsonPath();
        return jsonPath.getString("snippet");

    }

    public static void generateAndStorePassword() {
        Faker faker = new Faker();
        generatedPassword = faker.internet().password(6, 8, true);
        System.out.println("Generated password: " + generatedPassword); // Optional for debugging
    }
    public static void savePasswordToConfig(String password) {
        String configFilePath = "./src/test/resources/config.properties"; // Adjust path
        Properties props = new Properties();

        try (FileInputStream in = new FileInputStream(configFilePath)) {
            props.load(in);
        } catch (IOException e) {
            System.out.println("config.properties not found or empty, continuing...");
        }

        try (FileOutputStream out = new FileOutputStream(configFilePath)) {
            props.setProperty("Password", generatedPassword);
            props.store(out, "Updated password");
            System.out.println("Password saved to config.properties.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
