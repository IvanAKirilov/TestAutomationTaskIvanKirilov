package BaseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
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
