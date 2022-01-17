package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage {

    private WebDriver driver;

    private By searchedProduct = By.xpath("//a[contains(text(), 'Faded Short Sleeve T-shirt')]");
    public By productPriceEl = By.xpath("//span[contains(text(), '$16.51')]");

    public SearchResultPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickTheSearchedProduct(){
        driver.findElement(searchedProduct).click();
    }

    public String getProductPrice(){
        return driver.findElement(productPriceEl).getText();
    }
}
