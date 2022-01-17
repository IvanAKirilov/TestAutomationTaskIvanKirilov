package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final WebDriver driver;
    private final By homePageSignInButton = By.xpath("//a[contains(text(), 'Sign in')]");
    private final By searchField = By.id("search_query_top");
    private String searchTerm = "Faded Short Sleeve T-shirt";

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public LoginPage clickSignInButton(){
        driver.findElement(homePageSignInButton).click();
        return new LoginPage(driver);
    }

    public void searchProduct(){
        driver.findElement(searchField).click();
        driver.findElement(searchField).sendKeys(searchTerm);
        driver.findElement(searchField).sendKeys(Keys.ENTER);
    }
}
