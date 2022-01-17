package checkProductPriceTests;

import BaseTest.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;

public class checkFadedTeeShirtPriceTest extends BaseTest {
    private WebDriver driver;

    @Test
    public void checkProductPriceTest() {
    homepage.searchProduct();
    searchResultPage.clickTheSearchedProduct();
    assertEquals(searchResultPage.getProductPrice(), "$16.51", "Price is not Equal!");
    }
}
