import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.*;
import utils.ConfigReader;

import java.io.FileReader;
import java.io.IOException;


public class TestBase {

    protected static PageFactory page;
    protected static WebDriver driver;
    protected static HomePage homePage;
    protected static LoginPage loginPage;
    protected static ProductPage productPage;
    protected static CartPage cartPage;
    protected static CheckoutPage checkoutPage;



    public static void prepareDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }


    @BeforeClass
    public void setup() {
        try {
            System.getProperties().load(new FileReader("src/test/resources/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        prepareDriver();
        driver.get(ConfigReader.getProperty("base_url"));
        page = new PageFactory(driver);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

