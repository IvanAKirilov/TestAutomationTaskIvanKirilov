package checkProductPriceTests;

import BaseTest.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class CheckFadedTeeShirtPriceTest extends BaseTest {

    @Test
    public void checkProductPriceTest() {
    homepage.searchProduct();
    searchResultPage.clickTheSearchedProduct();
    assertEquals(searchResultPage.getProductPrice(), "$16.51", "Price is not Equal!");
    }
}
