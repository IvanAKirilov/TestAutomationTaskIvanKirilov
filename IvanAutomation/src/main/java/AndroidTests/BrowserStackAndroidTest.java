package AndroidTests;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import com.browserstack.local.Local;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BrowserStackAndroidTest {

    AndroidDriver driver;
    Local localInstance;
    WebDriverWait wait;
    public static String USERNAME = "ivankirilov_ZcetyV";
    public static String AUTOMATE_KEY = "WoagpFHzaCqs3AbazTrv";


    @BeforeTest
    public void setUp() throws Exception {
        // Starts the Local Testing Connection
        Runtime.getRuntime().exec("./BrowserStackLocal --key WoagpFHzaCqs3AbazTrv --force-local")
                .waitFor(2000, TimeUnit.MILLISECONDS);

        DesiredCapabilities caps = new DesiredCapabilities();

        // Set the Local testing parameters
        caps.setCapability("browserstack.local", "true");

        // Accepts the SSL Certificates
        caps.setCapability("acceptSslCerts", "true");

        // Set your access credentials
        caps.setCapability("browserstack.user", USERNAME);
        caps.setCapability("browserstack.key", AUTOMATE_KEY);

        // Set URL of the application under test
        caps.setCapability("app", "GuruPlayAndroid");

        // Specify device and os_version for testing
        caps.setCapability("device", "Google Pixel 3");
        caps.setCapability("os_version", "9.0");

        // Set other BrowserStack capabilities
        caps.setCapability("project", "First Browserstack Test");
        caps.setCapability("build", "debug_b10Bet-dev.apk");
        caps.setCapability("name", "first test");

        // Screenshots?
        caps.setCapability("browserstack.debug", "true"); // to enable visual logs
        caps.setCapability("browserstack.networkLogs", "true"); // to enable network logs

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined abovе
        driver = new AndroidDriver
                (new URL("http://hub.browserstack.com/wd/hub"), caps);

    }

    /** APP-877 */
    @Test
    public void LoginWithRememberMe() throws InterruptedException {
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        driver.terminateApp("com.india.guruplay");
        wait.wait(2000);
        driver.activateApp("com.india.guruplay");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        driver.findElement(By.id("headerLogin"))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameInputView")))
                .click();
        driver.findElement(By.id("inputEditText"))
                .sendKeys("kirilovstgguru4");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordInputView")));
        driver.findElement(By.id("passwordInputView"))
                .click();
        driver.findElement(By.xpath("//*[@class='android.view.ViewGroup'][2]//*[@class='android.widget.EditText']")) // need ID for the text field of the Password field
                .sendKeys("MyTest123!");
        driver.findElement(By.id("rememberMeCheckbox"))
                .click();
        driver.findElement(By.id("loginButton"))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAccount")));
        driver.terminateApp("com.india.guruplay");
        driver.activateApp("com.india.guruplay");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameInputView")));
        Assert.assertEquals(driver.findElement(By.id("inputEditText")).
                getText(), "kirilovstgguru4");
        driver.findElement(By.id("showPasswordImageView"))
                .click();
        /** Assert that the password is Displayed */
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='android.view.ViewGroup'][2]//*[@class='android.widget.EditText']")) // need ID for the text field of the Password field
                .getText(), "MyTest123!");
        driver.findElement(By.id("loginButton"))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAccount")))
                .click();
        /** Wait to be visible and click the Log out button */
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='androidx.recyclerview.widget.RecyclerView'][2]//*[@class='android.widget.FrameLayout'][3]//*[@class='android.widget.TextView']")))
                .click(); // ID here for the Log Out button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        driver.findElement(By.id("headerLogin"))
                .isDisplayed();
    }

    @AfterTest
    public void tearDownLocal() throws Exception {
        if (localInstance!=null){
            localInstance.stop();
        }
    }
    public void tearDown(){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
// set test status as pass
        jse.executeScript("browserstack_executor: {'action': 'setSessionStatus', 'arguments': {'status': 'passed', 'reason': 'Title matched!'}}");
// set test status as fail
        jse.executeScript("browserstack_executor: {'action': 'setSessionStatus', 'arguments': {'status': 'failed', 'reason': 'Title not matched'}}");
        if (driver != null){
            driver.quit();
        }
    }

}