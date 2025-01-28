package pages;

import org.openqa.selenium.WebDriver;

public class PageFactory {
    public final WebDriver driver;

    public PageFactory(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage HomePage() { return new HomePage(driver);}

    public ProductPage ProductPage() { return new ProductPage(driver);}
    public CartPage CartPage() { return new CartPage(driver);}
}
