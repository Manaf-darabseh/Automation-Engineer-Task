package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage{

    public CheckoutPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }
    public void addAddressAndPay() {
        driver.findElement(By.id("add-new-address-popover-link")).click();
        driver.findElement(By.id("address-ui-widgets-enterAddressFullName")).sendKeys("Your Name");
        driver.findElement(By.id("address-ui-widgets-enterAddressPhoneNumber")).sendKeys("0123456789");
        driver.findElement(By.id("address-ui-widgets-enterAddressLine1")).sendKeys("Your Address Line 1");
        driver.findElement(By.id("address-ui-widgets-enterAddressCity")).sendKeys("Your City");
        driver.findElement(By.id("address-ui-widgets-enterAddressPostalCode")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Use this address']")).click();

        driver.findElement(By.cssSelector("input[name='ppw-instrumentRowSelection']")).click();
    }

    public void verifyTotalAmount() {
        String totalAmount = driver.findElement(By.cssSelector("span#orderSummaryPrimaryTotalAmount")).getText();
        System.out.println("Total Amount: " + totalAmount);
    }
}
