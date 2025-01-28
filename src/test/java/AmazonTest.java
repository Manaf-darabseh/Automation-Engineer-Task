


import org.testng.annotations.Test;

public class AmazonTest extends TestBase {




    @Test(priority = 2)
    public void testNavigateToVideoGames() {

        page.HomePage().openAllMenu();
        page.HomePage().navigateToVideoGames();
    }

    @Test(priority = 3)
    public void testApplyFiltersAndSort() {
        page.ProductPage().applyFilters();
        page.ProductPage().sortByPriceHighToLow();
    }

    @Test(priority = 4)
    public void testAddProductsToCart() {
        page.ProductPage().addProductsBelow15k();
    }

    @Test(priority = 5)
    public void testCheckout() {
        page.CartPage().proceedToCheckout();
//        checkoutPage.addAddressAndPay();
//        checkoutPage.verifyTotalAmount();
    }
}

