package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutPage {

    private WebDriver driver;
    private By proceedButtonInCart = By.xpath("//p/a/span[contains(text(), 'Proceed to checkout')]");
    private By proceedButtonOnAddressAndShippingPages = By.xpath("//button/span[contains(text(), 'Proceed to checkout')]");
    private By termsCheckBox = By.id("cgv");
    private By paymentMethod = By.xpath("//a[contains(text(), 'Pay by bank wire')]");
    private By confirmOrder = By.xpath("//button/span[contains(text(), 'I confirm my order')]");
    public By confirmMessage = By.xpath("//div[@id='center_column']/div[1]/p/strong[contains(text(), 'Your order on My Store is complete')]");
    public By orderAmount = By.xpath("//div[@id='center_column']/div/span/strong[contains(text(), '$35.02')]");

    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickProceedInCart() {
        driver.findElement(proceedButtonInCart).click();
    }

    public void clickProceedOnAddressPage() {
        driver.findElement(proceedButtonOnAddressAndShippingPages).click();
    }

    public void clickTermsCheckBox() {
        driver.findElement(termsCheckBox).click();
    }

    public void clickPaymentMethod() {
        driver.findElement(paymentMethod).click();
    }

    public void clickConfirmOrder() {
        driver.findElement(confirmOrder).click();
    }

    public String getConfirmationMessage() {
        return driver.findElement(confirmMessage).getText();
    }

    public String getOrderAmount() {
        return driver.findElement(orderAmount).getText();
    }


}
