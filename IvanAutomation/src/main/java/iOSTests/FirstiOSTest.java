package iOSTests;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class FirstiOSTest {

    IOSDriver driver;
    TouchAction action;
    WebDriverWait wait;
    public String nativeContext = "NATIVE_APP";
    public String webContext = "WEBVIEW_com.india.guruplay";

    public void handleNotificationsPermissionPopUp() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Allow")));
        driver.findElement(By.id("Allow")).click();
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
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("automationName", "XCUITest");
        caps.setCapability("platformVersion", "14.4.2");
        caps.setCapability("deviceName", "Opentag's iPhone");
        caps.setCapability("udid","00008020-0018648014C1402E");
        caps.setCapability("xcodeOrgId","98XW3J38D8");
        caps.setCapability("xcodeSigningId", "iPhone Developer");
        caps.setCapability("useNewWDA",true);
        caps.setCapability("derivedDataPath", "/Users/ivankirilov/Library/Developer/Xcode/DerivedData/WebDriverAgent-ciegwgvxzxdrqthilmrmczmqvrgu/Build/Products/Debug-iphonesimulator/WebDriverAgentRunner-Runner.app");
        caps.setCapability("app","/Users/ivankirilov/IdeaProjects/TestAutomationTaskIvanKirilov/IvanAutomation/apps/dev_guruplay_1430.ipa");

        driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), caps);
    }

    @Test
    public void BalanceUpdateWithDeposit() throws InterruptedException {
        wait = new WebDriverWait(driver, 20);
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
//        String amount = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name='GuruPlay STG']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]")).getText();
        driver.findElement(By.id("Deposit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Fake Provider for Automation")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeOther[@name='Deposit']/XCUIElementTypeOther[5]/XCUIElementTypeTextField")));
        driver.findElement(By.xpath("//XCUIElementTypeOther[@name='Deposit']/XCUIElementTypeOther[5]/XCUIElementTypeTextField")).click();
//        driver.findElement(By.xpath("//XCUIElementTypeOther[@name='Deposit']/XCUIElementTypeOther[5]/XCUIElementTypeTextField")).sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), "50");
        driver.findElement(By.xpath("//XCUIElementTypeOther[@name='Deposit']/XCUIElementTypeOther[5]/XCUIElementTypeTextField")).clear();
        driver.findElement(By.xpath("//XCUIElementTypeOther[@name='Deposit']/XCUIElementTypeOther[5]/XCUIElementTypeTextField")).sendKeys("50");
        driver.findElement(By.id("Deposit ₹50")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Success"))).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeOther[@name='Deposit']/XCUIElementTypeOther[5]/XCUIElementTypeTextField")));
//        driver.context(nativeContext);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Close"))).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAmount")));
//        String updatedAmount = driver.findElement(By.id("headerAmount")).getText();
//        Assert.assertNotEquals(amount, updatedAmount);
    }

    @Test
    public void Test() throws InterruptedException {
        wait = new WebDriverWait(driver, 20);
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
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
