import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class UserTestAPI {

    private String baseUrl;
    private Properties properties;
    public static final org.slf4j.Logger log = LoggerFactory.getLogger(UserTestAPI.class);

    @BeforeClass
    public void setup() throws IOException {
        properties = new Properties();
        FileInputStream input = new FileInputStream("src/test/resources/config.properties"); // Path to your properties file
        properties.load(input);

        baseUrl = properties.getProperty("base.url");
        RestAssured.baseURI = baseUrl;
    }

    @Test
    public void createUser() {
        String requestBody = "{\"name\": \"Manaf Darabseh\", \"job\": \"Software Engineer In Test\", \"age\": \"34\"}";

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201) // Validate status code
                .extract().response();


        String errorMessage = response.jsonPath().getString("error");
        log.error("Invalid user update: {}", errorMessage);

        // Validate response body (example)
        String id = response.jsonPath().getString("id");
        assertEquals(response.jsonPath().getString("name"), "Manaf Darabseh");
        assertEquals(response.jsonPath().getString("job"), "Software Engineer In Test");
        assertNotNull(id); // Ensure ID is generated

        // Store the ID for the next test
        properties.setProperty("user.id", id); // Store in properties for later retrieval.  Better approach would be to return it from method
        try {
            properties.store(new java.io.FileOutputStream("src/test/resources/config.properties"), null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test(dependsOnMethods = "createUser") // Ensure createUser runs first
    public void retrieveUser() throws IOException {
        String userId = properties.getProperty("user.id");

        Response response = given()
                .when()
                .get("/api/users/" + userId)
                .then()
                .statusCode(200)
                .extract().response();

        String errorMessage = response.jsonPath().getString("error");
        log.error("Invalid user update: {}", errorMessage);

        assertEquals(response.jsonPath().getString("data.first_name"), "Manaf Darabseh");  //Adjust for the "data" wrapper in the response
        assertEquals(response.jsonPath().getString("data.id"), properties.getProperty("user.id"));
    }

    @Test(dependsOnMethods = "retrieveUser")
    public void updateUser() throws IOException {
        String userId = properties.getProperty("user.id");
        String requestBody = "{\"name\": \"m d\", \"job\": \"Senior Software Engineer\"}";

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put("/api/users/" + userId)
                .then()
                .statusCode(200)
                .extract().response();

        String errorMessage = response.jsonPath().getString("error");
        log.error("Invalid user update: {}", errorMessage);

        assertEquals(response.jsonPath().getString("name"), "Jane Doe");
        assertEquals(response.jsonPath().getString("job"), "Senior Software Engineer");
    }

}