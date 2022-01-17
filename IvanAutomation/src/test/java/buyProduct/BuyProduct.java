package buyProduct;

import BaseTest.BaseTest;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class BuyProduct extends BaseTest {

    @Test
    public void buyTwoProductsTest() throws InterruptedException {
        homepage.searchProduct();
        searchResultPage.clickTheSearchedProduct();
        productPage.changeQuantity();
        productPage.changeSize();
        productPage.changeColor();
        productPage.clickAddToCartButton();
        Thread.sleep(3000);
        String cartTotalPrice = productPage.getCartTotal();
        Thread.sleep(3000);
        productPage.clickProceedButton();
        Thread.sleep(2000);
        checkOutPage.clickProceedInCart();
        loginPage.userLoginIn();
        checkOutPage.clickProceedOnAddressPage();
        checkOutPage.clickTermsCheckBox();
        checkOutPage.clickProceedOnAddressPage();
        checkOutPage.clickPaymentMethod();
        checkOutPage.clickConfirmOrder();
        assertEquals(checkOutPage.getConfirmationMessage(), "Your order on My Store is complete.",
                "Your order is not complete");
        String finalAmount = checkOutPage.getOrderAmount();
        assertEquals(finalAmount,cartTotalPrice , "finalAmount is not equal to totalAmount");
    }
}
