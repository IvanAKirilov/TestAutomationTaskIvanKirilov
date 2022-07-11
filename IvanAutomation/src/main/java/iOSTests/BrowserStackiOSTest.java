package iOSTests;

import com.browserstack.local.Local;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BrowserStackiOSTest {

    IOSDriver driver;
    Local localInstance;
    WebDriverWait wait;
    TouchAction action;
    public static String USERNAME = "ivankirilov_ZcetyV";
    public static String AUTOMATE_KEY = "WoagpFHzaCqs3AbazTrv";

    public void handleNotificationsPermissionPopUp() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Don’t Allow")));
        driver.findElement(By.id("Don’t Allow")).click();
    }
    private void scrollDown() {
        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.8);
        int scrollEnd = (int) (dimension.getHeight() * 0.1);

        action = new AndroidTouchAction(driver)
                .press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                .moveTo(PointOption.point(0, scrollEnd))
                .release()
                .perform();
    }

    private void scrollUp() {
        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.1);
        int scrollEnd = (int) (dimension.getHeight() * 0.8);

        action = new AndroidTouchAction(driver)
                .press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                .moveTo(PointOption.point(0, scrollEnd))
                .release()
                .perform();
    }

    public boolean isDisabled(String element) {
        return !driver.findElement(By.id(element)).isEnabled();
    }

    @BeforeTest
    public void setUp() throws Exception {

        // Starts the Local Testing Connection
        Runtime.getRuntime().exec("./BrowserStackLocal --key WoagpFHzaCqs3AbazTrv --force-local")
                .waitFor(2000, TimeUnit.MILLISECONDS);

        DesiredCapabilities caps = new DesiredCapabilities();

        // Set the Local testing parameters
        caps.setCapability("browserstack.local", "true");
        caps.setCapability("acceptSslCerts", "true");

        // Set your access credentials
        caps.setCapability("browserstack.user", USERNAME);
        caps.setCapability("browserstack.key", AUTOMATE_KEY);

        // Set URL of the application under test
        caps.setCapability("app", "GuruPlayiOS");

        // Specify device and os_version for testing
        caps.setCapability("device", "iPhone 13");
        caps.setCapability("os_version", "15.1");

        // Set other BrowserStack capabilities
        caps.setCapability("project", "First Browserstack Test");
        caps.setCapability("build", "dev_guruplay"); // find better parametrized naming
        caps.setCapability("name", "first test");

        // Screenshots?
        caps.setCapability("browserstack.debug", "true"); // to enable visual logs
        caps.setCapability("browserstack.networkLogs", "true"); // to enable network logs

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined abovе
        driver = new IOSDriver
                (new URL("http://hub.browserstack.com/wd/hub"), caps);
    }

    @Test
    public void Test() throws InterruptedException {
        wait = new WebDriverWait(driver, 20);
        handleNotificationsPermissionPopUp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Log in")));
        driver.terminateApp("com.india.guruplay");
        driver.activateApp("com.india.guruplay");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Log in")));
        driver.findElement(By.id("Log in"))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UsernameField")))
                .click();
        driver.findElement(By.id("UsernameField"))
                .sendKeys("kirilovstgguru4");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PasswordField")));
        driver.findElement(By.id("PasswordField"))
                .click();
        driver.findElement(By.id("PasswordField"))
                .sendKeys("MyTest123!");
        driver.findElement(By.xpath("(//XCUIElementTypeButton[@name='Log in'])[2]"))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Deposit")));
        driver.findElement(By.id("Deposit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Fake Provider for Automation")));
        driver.findElement(By.id("Deposit ₹300")).click();
        Thread.sleep(10000);
    }

    /** APP-877 */
    @Test
    public void LoginWithRememberMe() {
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        driver.terminateApp("com.india.guruplay");
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
        driver.findElement(By.xpath("//*[@class='android.view.ViewGroup'][2]//*[@class='android.widget.EditText']"))
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
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='android.view.ViewGroup'][2]//*[@class='android.widget.EditText']"))
                .getText(), "MyTest123!");
        driver.findElement(By.id("loginButton"))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAccount")))
                .click();
        /** Wait to be visible and click the Log out button */
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='androidx.recyclerview.widget.RecyclerView'][2]//*[@class='android.widget.FrameLayout'][3]//*[@class='android.widget.TextView']")))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        driver.findElement(By.id("headerLogin"))
                .isDisplayed();
    }

    /** APP-1359 */
    @Test
    public void SuccessfulLogin() {
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        driver.terminateApp("com.india.guruplay");
        driver.activateApp("com.india.guruplay");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        scrollDown();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("casinoFirstSectionView")));
        /** Click the first game from the casino section within the Home screen */
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.ImageView"))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("viewCredentialsBackImageView")));
        /** Click the 'X' button for the Login pop-up */
        driver.findElement(By.id("viewCredentialsBackImageView"))
                .click();
        scrollUp();
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
        driver.findElement(By.xpath("//*[@class='android.view.ViewGroup'][2]//*[@class='android.widget.EditText']"))
                .sendKeys("MyTest123!");
        driver.findElement(By.id("loginButton")).isEnabled();
        driver.findElement(By.id("showPasswordImageView"))
                .click();
        /** Assert the password is Displayed */
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='android.view.ViewGroup'][2]//*[@class='android.widget.EditText']"))
                .getText(), "MyTest123!");
        driver.findElement(By.id("showPasswordImageView"))
                .click();
        /** Assert password is hidden */
        Assert.assertNotEquals(driver.findElement(By.xpath("//*[@class='android.view.ViewGroup'][2]//*[@class='android.widget.EditText']"))
                .getText(), "MyTest123!"); // verify password is hidden
        driver.findElement(By.xpath("//*[@class='android.view.ViewGroup'][2]//*[@class='android.widget.EditText']"))
                .clear();
        isDisabled("loginButton");
        driver.findElement(By.xpath("//*[@class='android.view.ViewGroup'][2]//*[@class='android.widget.EditText']"))
                .sendKeys("MyTest123!");
        driver.findElement(By.id("inputEditText"))
                .clear();
        isDisabled("loginButton");
        driver.findElement(By.id("inputEditText"))
                .sendKeys("kirilovstgguru4");
        driver.findElement(By.id("loginButton"))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAccount"))).isDisplayed();
    }

    /** APP-867 */
    @Test
    public void UnsuccessfulLogin() {
        wait = new WebDriverWait(driver, 20);
        handleNotificationsPermissionPopUp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Log in")));
        driver.terminateApp("com.india.guruplay");
        driver.activateApp("com.india.guruplay");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Log in")));
        driver.findElement(By.id("Log in"))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UsernameField")))
                .click();
        driver.findElement(By.id("UsernameField"))
                .sendKeys("kirilovstgguru4");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PasswordField")));
        driver.findElement(By.id("PasswordField"))
                .click();
        driver.findElement(By.id("PasswordField"))
                .sendKeys("MyTest1234!");
        driver.findElement(By.xpath("(//XCUIElementTypeButton[@name='Log in'])[2]"))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Invalid credentials")));
        Assert.assertEquals(driver.findElement(By.id("Invalid credentials")).getText(), "Invalid credentials");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Log in")));
        driver.findElement(By.id("Log in"))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UsernameField")))
                .click();
        driver.findElement(By.xpath("//XCUIElementTypeOther[@name='UsernameField']/XCUIElementTypeOther/XCUIElementTypeTextField")) // we need ID here
                .clear();
        driver.findElement(By.id("UsernameField"))
                .sendKeys("kirilovstgguru40");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PasswordField")));
        driver.findElement(By.xpath("//XCUIElementTypeOther[@name='PasswordField']/XCUIElementTypeOther/XCUIElementTypeSecureTextField")) // we need ID here
                .clear();
        driver.findElement(By.id("PasswordField"))
                .sendKeys("MyTest123!");
        driver.findElement(By.id("(//XCUIElementTypeButton[@name='Log in'])[2]")) // need ID here
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorTextView")));
        Assert.assertEquals(driver.findElement(By.id("errorTextView")).getText(), "Invalid credentials");
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