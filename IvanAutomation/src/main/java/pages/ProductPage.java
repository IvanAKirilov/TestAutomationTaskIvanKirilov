package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class ProductPage {

    private  WebDriver driver;
    private  By quantityField = By.id("quantity_wanted");
    private  By sizeDropDown = By.id("group_1");
    private  By selectSizeM = By.xpath("//option[@title='M']");
    private  By blueColor = By.id("color_14");
    private  By addToCartButton = By.xpath("//span[contains(text(), 'Add to cart')]");
    private  By totalPrice = By.xpath("//span[@class='ajax_block_cart_total']");
    private  By proceedButton = By.xpath("//span[contains(text(), 'Proceed to checkout')]");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void changeQuantity(){
        driver.findElement(quantityField).click();
        driver.findElement(quantityField).sendKeys(Keys.BACK_SPACE);
        driver.findElement(quantityField).sendKeys("2");
    }

    public void changeSize(){
        driver.findElement(sizeDropDown).click();
        driver.findElement(selectSizeM).click();
    }

    public void changeColor(){
        driver.findElement(blueColor).click();
    }

    public void clickAddToCartButton(){
        driver.findElement(addToCartButton).click();
    }

    public String getCartTotal(){
       return driver.findElement(totalPrice).getText();
    }

    public void clickProceedButton(){
        driver.findElement(proceedButton).click();
    }

}
