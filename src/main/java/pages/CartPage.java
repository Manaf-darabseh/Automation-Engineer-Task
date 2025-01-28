package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage{

    @FindBy(xpath = "//a[@id='nav-cart']")
    WebElement cart;

    @FindBy(xpath = "//input[@name='proceedToRetailCheckout']")
    WebElement ProceedToBuy;
    public CartPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }
    public void proceedToCheckout() {
        jsClick(cart);
        ClickOn(ProceedToBuy);

    }
}
