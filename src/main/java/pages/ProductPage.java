package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class ProductPage extends BasePage{

    @FindBy(xpath = "(//ul[@aria-labelledby='p_n_free_shipping_eligible-title']//span[text()='Free Shipping'])[2]")
    WebElement FreeShipping;

    @FindBy(xpath = "//div[@id='p_n_condition-type']//span[text()='New']")
    WebElement NEW;

    @FindBy(id = "s-result-sort-select")
    WebElement SortDDList;

    @FindBy(xpath = "//a[text()='Price: High to Low']")
    WebElement H2Low;

    @FindBy(xpath = "//div[@data-component-type='s-search-result']")
    WebElement productList;

    public ProductPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }
    public void applyFilters() {
        ClickOn(FreeShipping);
        waitForPageToLoad();
        ClickOn(NEW);
        waitForPageToLoad();
    }

    public void sortByPriceHighToLow() {
        jsClick(SortDDList);
        ClickOn(H2Low);
        waitForPageToLoad();
    }

    public void addProductsBelow15k() {
        waitForPageToLoad();
        fluentWaitForElement(productList);
        boolean productsAdded = false;

        while (!productsAdded) {
            List<WebElement> products = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));
            int index = 0;

            while (index < products.size()) {
                WebElement product = products.get(index);
                try {
                    fluentWaitForElement(product);
                    WebElement priceElement = product.findElement(By.xpath(".//span[@class='a-price-whole']"));
                    fluentWaitForElement(priceElement);
                    String priceText = priceElement.getText().replace(",", "");
                    double price = Double.parseDouble(priceText);

                    if (price < 15000) {
                        WebElement addToCartButton = product.findElement(By.xpath(".//button[text()='Add to cart']"));
                        addToCartButton.click();
                        System.out.println("Product added to cart: " + price);
                        productsAdded = true; // At least one product was added
                    }
                } catch (Exception e) {
                    System.out.println("Skipping a product due to error: " + e.getMessage());
                }
                index++; // Move to the next product
            }

            // If no products were added, move to the next page
            if (!productsAdded) {
                try {
                    WebElement nextPageButton = driver.findElement(By.xpath("//a[contains(text(), 'Next')]"));
                    if (nextPageButton.isEnabled()) {
                        nextPageButton.click();
                        System.out.println("Moving to the next page...");
                        // Wait for the next page to load
                        Thread.sleep(3000); // Adjust sleep time as needed
                    } else {
                        System.out.println("No more pages to navigate.");
                        break; // Exit the loop if there are no more pages
                    }
                } catch (Exception e) {
                    System.out.println("Failed to navigate to the next page: " + e.getMessage());
                    break; // Exit the loop if navigation fails
                }
            }
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (productsAdded) {
            System.out.println("Products below 15k EGP were added to the cart.");
        } else {
            System.out.println("No products below 15k EGP were found.");
        }
    }


}
