package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    private By emailField = By.id("email");
    private By passwordField = By.id("passwd");
    private String username = "xew@abv.bg";
    private String password = "TestParola111!";
    private By signInButton = By.id("SubmitLogin");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void userLoginIn(){
        driver.findElement(emailField).click();
        driver.findElement(emailField).sendKeys(username);
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(signInButton).click();
    }
}
