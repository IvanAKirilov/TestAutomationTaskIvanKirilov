package BaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.*;

public class BaseTest {

    private WebDriver driver;
    public HomePage homepage;
    public SearchResultPage searchResultPage;
    public LoginPage loginPage;
    public ProductPage productPage;
    public CheckOutPage checkOutPage;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com");
        driver.manage().window().maximize();
        homepage = new HomePage(driver);
        searchResultPage = new SearchResultPage(driver);
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        checkOutPage = new CheckOutPage(driver);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
