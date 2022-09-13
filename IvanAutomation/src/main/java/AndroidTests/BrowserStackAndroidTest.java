package AndroidTests;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import com.browserstack.local.Local;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

public class BrowserStackAndroidTest {

    AndroidDriver driver;
    Local localInstance;
    WebDriverWait wait;
    AndroidTouchAction action;
    public static String USERNAME = "ivan_jI6MHB";
    public static String AUTOMATE_KEY = "hm3yAxiAASNPs7rAbpgR";
    public String nativeContext = "NATIVE_APP";
    public String webContext = "WEBVIEW_com.india.guruplay";

    private void killAndRelaunchApp(String packageName) {
        driver.terminateApp(packageName);
        driver.activateApp(packageName);
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

    private boolean findElement(String element) {
        List<? extends WebElement> el = driver.findElements(By.id(element));
        return el.size() <= 0;
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

    private void swipeRight() {
        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getWidth() * 0.8);
        int scrollEnd = (int) (dimension.getWidth() * 0.1);
        WebElement element = driver.findElement(By.id("viewPager"));
        action = new AndroidTouchAction(driver)
                .press(ElementOption.element(element))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                .moveTo(PointOption.point(scrollStart, scrollEnd))
                .release()
                .perform();
    }

    private void swipeLeft(int starSwipeX, int startSwipeY, int endSwipeX, int endSwipeY) {
        action = new AndroidTouchAction(driver)
                .press(PointOption.point(starSwipeX, startSwipeY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                .moveTo(PointOption.point(endSwipeX, endSwipeY))
                .release()
                .perform();
    }

    public boolean isDisabled(String element) {
        return !driver.findElement(By.id(element)).isEnabled();
    }

    public boolean isPresent(String element) {
        List<? extends WebElement> el = driver.findElements(By.id(element));
        return el.size() > 0;
    }

    private void handelAppsUpdatePopUp() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        if (isPresent("android:id/button2")) {
            driver.findElement(By.id("android:id/button2")).click();
        } else {
            Assert.assertFalse(isPresent("android:id/button2"));
        }
    }


    private void waitForElementToAppear(String element) {
        Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10L))
                .pollingEvery(Duration.ofSeconds(2L))
                .ignoring(NoSuchElementException.class);
        wait1.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return driver.findElement(By.id(element));
            }
        });
    }

    private void LoginWithRememberMe() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        driver.findElement(By.id("headerLogin"))
                .click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameInputView"))).isDisplayed());
        driver.findElement(By.id("usernameInputView")).click();
        driver.findElement(By.id("inputEditText"))
                .sendKeys("kirilovstgguru2");
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordInputView"))).isDisplayed());
        driver.findElement(By.id("passwordInputView"))
                .click();
        driver.findElement(By.xpath("//*[@class='android.view.ViewGroup'][2]//*[@class='android.widget.EditText']")) // need ID for the text field of the Password field
                .sendKeys("MyTest123!");
        driver.findElement(By.id("rememberMeCheckbox"))
                .click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginButton"))).isEnabled());
        driver.findElement(By.id("loginButton"))
                .click();
    }

    private void Login() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        driver.findElement(By.id("headerLogin"))
                .click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameInputView"))).isDisplayed());
        driver.findElement(By.id("usernameInputView")).click();
        driver.findElement(By.id("inputEditText"))
                .sendKeys("kirilovstgguru2");
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordInputView"))).isDisplayed());
        driver.findElement(By.id("passwordInputView"))
                .click();
        driver.findElement(By.xpath("//*[@class='android.view.ViewGroup'][2]//*[@class='android.widget.EditText']")) // need ID for the text field of the Password field
                .sendKeys("MyTest123!");
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginButton"))).isEnabled());
        driver.findElement(By.id("loginButton"))
                .click();
    }

    private void Logout() {
        driver.findElement(By.id("headerAccount")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountMenuNonGridItems")));
        WebElement accountGridSection = driver.findElement(By.id("accountMenuNonGridItems"));
        List<WebElement> accountGridElements = accountGridSection.findElements(By.className("android.widget.FrameLayout"));
        accountGridElements.get(2).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin"))).isDisplayed());
    }

    @BeforeTest
    public void BrowserStackLocalStart() throws IOException, InterruptedException {
        // Starts the Local Testing Connection
        Runtime.getRuntime().exec("./BrowserStackLocal --key hm3yAxiAASNPs7rAbpgR --force-local")
                .waitFor(2000, TimeUnit.MILLISECONDS);
    }

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
// Starts the Local Testing Connection
//        Runtime.getRuntime().exec("./BrowserStackLocal --key hm3yAxiAASNPs7rAbpgR --force-local")
//                .waitFor(2000, TimeUnit.MILLISECONDS);
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
        caps.setCapability("build", "Android_Guru_Test");
        caps.setCapability("name", "GuruSmoke");

        // Screenshots?
        caps.setCapability("browserstack.debug", "true"); // to enable visual logs
        caps.setCapability("browserstack.networkLogs", "true"); // to enable network logs

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined abovе
        driver = new AndroidDriver
                (new URL("http://hub.browserstack.com/wd/hub"), caps);

    }

    /**
     * APP-877
     */
    @Test
    public void LoginWithRememberMeFunctionality() {
        wait = new WebDriverWait(driver, Duration.ofNanos(20000));
        handelAppsUpdatePopUp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        killAndRelaunchApp("com.india.guruplay");
        LoginWithRememberMe();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAccount"))).isDisplayed());
        killAndRelaunchApp("com.india.guruplay");
        LoginWithRememberMe();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAccount"))).isDisplayed());
        Logout();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (isPresent("headerLogin")) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Element Displayed!\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Element is not Displayed!\"}}");
        }
    }

    /**
     * APP-1359
     */
    @Test(groups = {"StagingSmoke"})
    public void SuccessfulLogin() {
        wait = new WebDriverWait(driver, Duration.ofNanos(20000));
        handelAppsUpdatePopUp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        killAndRelaunchApp("com.india.guruplay");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        scrollDown();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("casinoFirstSectionView"))).isDisplayed());
        /** Click the first game from the casino section within the Home screen */
        WebElement casinoHomeSection = driver.findElement(By.id("casinoFirstSectionView"));
        List<WebElement> casinoHomeSectionElements = casinoHomeSection.findElements(By.className("android.widget.LinearLayout"));
        List<WebElement> casinoHomeTabElements = casinoHomeSectionElements.get(1).findElements(By.className("android.widget.LinearLayout"));
        wait.until(ExpectedConditions.visibilityOf(casinoHomeTabElements.get(0)));
        casinoHomeTabElements.get(0).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("viewCredentialsBackImageView"))).isDisplayed());
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
        driver.findElement(By.xpath("//*[@class='android.view.ViewGroup'][2]//*[@class='android.widget.EditText']")) // need ID for the text field of the Password field
                .sendKeys("MyTest123!");
        Assert.assertTrue(driver.findElement(By.id("loginButton")).isEnabled());
        driver.findElement(By.id("showPasswordImageView"))
                .click();
        /** Assert the password is Displayed */
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='android.view.ViewGroup'][2]//*[@class='android.widget.EditText']")) // need ID for the text field of the Password field
                .getText(), "MyTest123!");
        driver.findElement(By.id("showPasswordImageView"))
                .click();
        /** Assert password is hidden */
        Assert.assertNotEquals(driver.findElement(By.xpath("//*[@class='android.view.ViewGroup'][2]//*[@class='android.widget.EditText']")) // need ID for the text field of the Password field
                .getText(), "MyTest123!"); // verify password is hidden
        driver.findElement(By.xpath("//*[@class='android.view.ViewGroup'][2]//*[@class='android.widget.EditText']")) // need ID for the text field of the Password field
                .clear();
        isDisabled("loginButton");
        driver.findElement(By.xpath("//*[@class='android.view.ViewGroup'][2]//*[@class='android.widget.EditText']")) // need ID for the text field of the Password field
                .sendKeys("MyTest123!");
        driver.findElement(By.id("inputEditText"))
                .clear();
        isDisabled("loginButton");
        driver.findElement(By.id("inputEditText"))
                .sendKeys("kirilovstgguru4");
        driver.findElement(By.id("loginButton"))
                .click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAccount"))).isDisplayed());
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (isPresent("headerAccount")) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Element Displayed!\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Element is not Displayed!\"}}");
        }
    }

    // CHECK WHY ON THE SECOND TRY THERE IS NO ERROR MESSAGE

    /**
     * APP-867
     */
    @Test(groups = {"StagingSmoke"})
    public void UnsuccessfulLogin() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofNanos(20000));
        handelAppsUpdatePopUp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        killAndRelaunchApp("com.india.guruplay");
//        driver.toggleWifi();
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
                .sendKeys("MyTest1234!");
        driver.findElement(By.id("loginButton"))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorTextView")));
        Assert.assertEquals(driver.findElement(By.id("errorTextView")).getText(), "Invalid credentials");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        driver.findElement(By.id("headerLogin"))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameInputView")))
                .click();
        driver.findElement(By.id("inputEditText"))
                .sendKeys("kirilovstgguru40");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordInputView")));
        driver.findElement(By.id("passwordInputView"))
                .click();
        driver.findElement(By.xpath("//*[@class='android.view.ViewGroup'][2]//*[@class='android.widget.EditText']")) // need ID for the text field of the Password field
                .sendKeys("MyTest123!");
        Thread.sleep(3000);
        driver.findElement(By.id("loginButton"))
                .click();
        driver.findElement(By.id("loginButton"))
                .click();
        driver.findElement(By.id("loginButton"))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorTextView")));
        Assert.assertEquals(driver.findElement(By.id("errorTextView")).getText(), "Invalid credentials");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (driver.findElement(By.id("errorTextView")).getText().equals("Invalid credentials")) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Error text matched!\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Error text does not match!\"}}");
        }
    }

    @Test(groups = {"StagingSmoke"})
    public void BalanceUpdateWithDeposit() {
        wait = new WebDriverWait(driver, Duration.ofNanos(20000));
        handelAppsUpdatePopUp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        killAndRelaunchApp("com.india.guruplay");
        Login();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAmount"))).isDisplayed());
        String amount = driver.findElement(By.id("headerAmount")).getText();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerDeposit"))).click();
        System.out.println(driver.getContextHandles());
        driver.context(webContext);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-uat='deposit-Amount *-field']")));
        driver.findElement(By.xpath("//*[@data-uat='deposit-Amount *-field']")).click();
        driver.findElement(By.xpath("//*[@data-uat='deposit-Amount *-field']")).sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), "50");
        driver.hideKeyboard();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-uat='deposit-submit']")));
        driver.findElement(By.xpath("//*[@data-uat='deposit-submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='success']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-uat='deposit-Amount *-field']")));
        driver.context(nativeContext);
        driver.findElement(By.id("fragmentWebDepositCloseButton")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAmount")));
        String updatedAmount = driver.findElement(By.id("headerAmount")).getText();
        Assert.assertNotEquals(amount, updatedAmount);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (!amount.equals(updatedAmount)) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Amounts are different!\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Amounts are equals\"}}");
        }

    }

    @Test(groups = {"StagingSmoke"})
    public void BalanceUpdateWithWithdrawal() {
        wait = new WebDriverWait(driver, Duration.ofNanos(20000));
        handelAppsUpdatePopUp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        killAndRelaunchApp("com.india.guruplay");
        Login();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAccount"))).isDisplayed());
        String amount = driver.findElement(By.id("headerAmount")).getText();
        driver.findElement(By.id("headerAccount")).click();
        WebElement accountMenuGrid = driver.findElement(By.id("accountMenuGridItems"));
        List<WebElement> accountMenuGridElements = accountMenuGrid.findElements(By.className("android.widget.RelativeLayout"));
        System.out.println(accountMenuGridElements.get(2).findElement(By.className("android.widget.TextView")).getText());
        wait.until(ExpectedConditions.visibilityOf(accountMenuGridElements.get(2)));
        accountMenuGridElements.get(2).click();
        driver.context(webContext);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-uat='withdraw-submit']")));
        driver.findElement(By.xpath("//*[@data-uat='withdraw-Amount *-field']")).click();
        driver.findElement(By.xpath("//*[@data-uat='withdraw-Amount *-field']")).sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), "50");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//*[@data-uat='withdraw-submit']")).click();
        driver.context(nativeContext);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogoImageView")))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAmount")));
        String updatedAmount = driver.findElement(By.id("headerAmount")).getText();
        Assert.assertNotEquals(amount, updatedAmount);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (!amount.equals(updatedAmount)) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Amounts are different!\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Amounts are equals\"}}");
        }
    }

    @Test(groups = {"StagingSmoke"})
    public void ChatFunctionality() {
        wait = new WebDriverWait(driver, Duration.ofNanos(20000));
        handelAppsUpdatePopUp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        killAndRelaunchApp("com.india.guruplay");
        Login();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAmount"))).isDisplayed());
        driver.findElement(By.id("headerAccount")).click();
        WebElement accountMenuGrid = driver.findElement(By.id("accountMenuGridItems"));
        List<WebElement> accountMenuGridElements = accountMenuGrid.findElements(By.className("android.widget.RelativeLayout"));
        System.out.println(accountMenuGridElements.get(8).findElement(By.className("android.widget.TextView")).getText());
        wait.until(ExpectedConditions.visibilityOf(accountMenuGridElements.get(8)));
        accountMenuGridElements.get(8).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerMinimiseChat")));
        driver.findElement(By.id("headerMinimiseChat")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("chat")));
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAccount"))).isDisplayed());
        Logout();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin"))).isDisplayed());
        driver.findElement(By.id("chat"))
                .isDisplayed();
        driver.findElement(By.id("chat"))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogoImageView")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ce_bubble_content_text_view")));
        Assert.assertTrue(driver.findElement(By.id("ce_bubble_content_text_view")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("input_views")).isDisplayed());
        driver.findElement(By.id("headerEndChat")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("chat_close")));
        driver.findElement(By.id("chat_close"))
                .click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin"))).isDisplayed());
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (isPresent("headerLogin")) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Element is Displayed!\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Element is not Displayed!\"}}");
        }
    }

    // THINK OF MORE SMART WAY FOR THIS TEST, 2 users, with different amount
    @Test(groups = {"StagingSmoke"})
    public void LowFundsBanner() {
        wait = new WebDriverWait(driver, Duration.ofNanos(20000));
        handelAppsUpdatePopUp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        killAndRelaunchApp("com.india.guruplay");
        Login();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lowBalanceView"))).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("viewLowBalanceImage")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("viewLowBalanceText")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("viewLowBalanceCloseContainer")).isDisplayed());
        driver.findElement(By.id("viewLowBalanceDepositContainer")).click();
        driver.context(webContext);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-uat='deposit-Amount *-field']")))
                .isDisplayed());
        driver.findElement(By.xpath("//*[@data-uat='deposit-Amount *-field']"))
                .click();
        driver.findElement(By.xpath("//*[@data-uat='deposit-Amount *-field']"))
                .sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), "50");
        driver.hideKeyboard();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-uat='deposit-submit']")));
        driver.findElement(By.xpath("//*[@data-uat='deposit-submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='success']")))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-uat='deposit-Amount *-field']")));
        driver.context(nativeContext);
        driver.findElement(By.id("fragmentWebDepositCloseButton"))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogoImageView")));
        Assert.assertFalse(isPresent("viewLowBalanceImage"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (isPresent("viewLowBalanceImage")) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Element is Displayed!\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Element is not Displayed!\"}}");
        }
    }

    @Test(groups = {"StagingSmoke"})
    public void NavigationThroughBottomNavigationTabs() {
        wait = new WebDriverWait(driver, Duration.ofNanos(20000));
        handelAppsUpdatePopUp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        killAndRelaunchApp("com.india.guruplay");
        wait.until(ExpectedConditions.visibilityOfElementLocated(new MobileBy.ByAccessibilityId("Sports")))
                .click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(new MobileBy.ByAccessibilityId("Top Games"))).isDisplayed());
        driver.findElement(new MobileBy.ByAccessibilityId("Casino")).click();
        driver.context(webContext);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-uat='casino-search-button']"))).isDisplayed());
        driver.context(nativeContext);
        driver.findElement(new MobileBy.ByAccessibilityId("Live Casino")).click();
        driver.context(webContext);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@name='Live Casino Category']"))).isDisplayed());
        driver.context(nativeContext);
        driver.findElement(new MobileBy.ByAccessibilityId("Offers")).click();
        driver.context(webContext);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@href='/promotions/VladiTestPromo/']"))).isDisplayed());
        driver.context(nativeContext);
        driver.findElement(new MobileBy.ByAccessibilityId("Discover")).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(new MobileBy.ByAccessibilityId("Download The App"))).isDisplayed());
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (driver.findElement(new MobileBy.ByAccessibilityId("Download The App")).isDisplayed()) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Element is Displayed!\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Element is not Displayed!\"}}");
        }
    }

    @Test(groups = {"StagingSmoke"})
    public void HomeBannersCarouselOverview() {
        wait = new WebDriverWait(driver, Duration.ofNanos(20000));
        handelAppsUpdatePopUp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        killAndRelaunchApp("com.india.guruplay");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("viewPager")));
        Login();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("viewPager"))).isDisplayed());
        for (int i = 0; i < 4; i++) {
            swipeRight();
        }
        for (int i = 0; i < 4; i++) {
            swipeLeft(700, 500, 0, 500);
        }
        driver.findElement(By.id("itemImage")).click();
        driver.context(webContext);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-uat='deposit-Amount *-field']"))).isDisplayed());
        driver.context(nativeContext);
        driver.findElement(By.id("fragmentWebDepositCloseButton")).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("itemImage"))).isDisplayed());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAccount"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountMenuNonGridItems")));
        Logout();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (isPresent("headerLogin")) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Element is Displayed!\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Element is not Displayed!\"}}");
        }
    }

    @Test(groups = {"StagingSmoke"})
    public void HomePromoSectionOverview() {
        wait = new WebDriverWait(driver, Duration.ofNanos(20000));
        handelAppsUpdatePopUp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        killAndRelaunchApp("com.india.guruplay");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("viewPager")));
        Login();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("promotionSectionView"))).isDisplayed());
        driver.findElement(By.id("showAllButtonContainer")).click();
        driver.context(webContext);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@href='/promotions/VladiTestPromo/']"))).isDisplayed());
        driver.context(nativeContext);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogoImageView"))).click();
        WebElement promoHomeSection = driver.findElement(By.id("promotionSectionView"));
        List<WebElement> promoHomeSectionElements = promoHomeSection.findElements(By.className("android.widget.LinearLayout"));
        List<WebElement> promoHomeTabElements = promoHomeSectionElements.get(1).findElements(By.className("android.widget.LinearLayout"));
        wait.until(ExpectedConditions.visibilityOf(promoHomeTabElements.get(0)));
        promoHomeSectionElements.get(0).click();
        driver.context(webContext);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@href='/promotions/VladiTestPromo/']"))).isDisplayed());
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (isPresent(String.valueOf(By.xpath("//*[@href='/promotions/VladiTestPromo/']")))) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Element is Displayed!\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Element is not Displayed!\"}}");
        }
    }

    @Test(groups = {"StagingSmoke"})
    public void HomeCasinoAndLiveCasinoSectionsOverview() {
        wait = new WebDriverWait(driver, Duration.ofNanos(60000));
        handelAppsUpdatePopUp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        killAndRelaunchApp("com.india.guruplay");
        Login();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAccount"))).isDisplayed());
        scrollDown();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("casinoFirstSectionView"))).isDisplayed());
        driver.findElement(By.id("showAllButtonContainer")).click();
        driver.context(webContext);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-uat='casino-search-button']"))).isDisplayed());
        driver.context(nativeContext);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogoImageView"))).click();
        scrollDown();
        WebElement casinoHomeSection = driver.findElement(By.id("casinoFirstSectionView"));
        List<WebElement> casinoHomeSectionElements = casinoHomeSection.findElements(By.className("android.widget.LinearLayout"));
        List<WebElement> casinoHomeTabElements = casinoHomeSectionElements.get(1).findElements(By.className("android.widget.LinearLayout"));
        wait.until(ExpectedConditions.visibilityOf(casinoHomeTabElements.get(0)));
        casinoHomeTabElements.get(0).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnBack"))).isDisplayed());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnBack"))).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("casinoFirstSectionView"))).isDisplayed());
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("casinoSecondSectionView"))).isDisplayed());
        WebElement LiveCasinoHomeSection = driver.findElement(By.id("casinoSecondSectionView"));
        List<WebElement> LiveCasinoHomeViewAllEl = LiveCasinoHomeSection.findElements(By.className("android.widget.LinearLayout"));
        List<WebElement> LiveCasinoHomeViewAllBtn = LiveCasinoHomeViewAllEl.get(0).findElements(By.className("android.widget.LinearLayout"));
        LiveCasinoHomeViewAllBtn.get(0).click();
        driver.context(webContext);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@name='Live Casino Category']"))).isDisplayed());
        driver.context(nativeContext);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogoImageView"))).click();
        scrollDown();
        List<WebElement> LiveCasinoHomeSectionElements = LiveCasinoHomeSection.findElements(By.className("android.widget.LinearLayout"));
        List<WebElement> LiveCasinoHomeTabElements = LiveCasinoHomeSectionElements.get(1).findElements(By.className("android.widget.LinearLayout"));
        wait.until(ExpectedConditions.visibilityOf(LiveCasinoHomeTabElements.get(0)));
        LiveCasinoHomeTabElements.get(0).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnBack"))).isDisplayed());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnBack"))).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("casinoSecondSectionView"))).isDisplayed());
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (isPresent("casinoSecondSectionView")) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Element is Displayed!\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Element is not Displayed!\"}}");
        }
    }

    @Test(groups = {"StagingSmoke"})
    public void OffersSectionOverview() {
        wait = new WebDriverWait(driver, Duration.ofNanos(20000));
        handelAppsUpdatePopUp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        killAndRelaunchApp("com.india.guruplay");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        scrollDown();
        scrollDown();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("footer"))).isDisplayed());
        WebElement OffersSection = driver.findElement(By.id("offersView"));
        List<WebElement> OffersSectionElements = OffersSection.findElements(By.className("android.widget.FrameLayout"));
        List<WebElement> OfferElements = OffersSectionElements.get(1).findElements(By.className("android.view.ViewGroup"));
        List<WebElement> OfferImageElement = OfferElements.get(0).findElements(By.className("android.widget.ImageView"));
        List<WebElement> OfferTitleElement = OfferElements.get(0).findElements(By.className("android.widget.TextView"));
        Assert.assertTrue(OfferImageElement.get(0).isDisplayed());
        Assert.assertTrue(OfferTitleElement.get(0).isDisplayed());
        OffersSectionElements.get(0).click();
        Assert.assertFalse(isPresent("offersView"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (isPresent("offersView")) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Element is Displayed!\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Element is not Displayed!\"}}");
        }
    }

    @Test(groups = {"StagingSmoke"})
    public void FooterOverview() {
        wait = new WebDriverWait(driver, Duration.ofNanos(20000));
        handelAppsUpdatePopUp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        killAndRelaunchApp("com.india.guruplay");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        scrollDown();
        scrollDown();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("footer"))).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("socialIcons")).isDisplayed());
        scrollDown();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("linksRecyclerView"))).isDisplayed());
        WebElement FooterLinksSection = driver.findElement(By.id("linksRecyclerView"));
        List<WebElement> FooterLinksElements = FooterLinksSection.findElements(By.className("android.widget.TextView"));
        FooterLinksElements.get(5).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(new MobileBy.ByAccessibilityId("Connection is secure. Site information"))).isDisplayed());
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(new MobileBy.ByAccessibilityId("Close tab"))).isDisplayed());
        driver.findElement(new MobileBy.ByAccessibilityId("Close tab")).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("linksRecyclerView"))).isDisplayed());
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (isPresent("linksRecyclerView")) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Element is Displayed!\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Element is not Displayed!\"}}");
        }
    }

    @Test(groups = {"StagingSmoke"})
    public void LicencesAndPaymentsOverview() {
        wait = new WebDriverWait(driver, Duration.ofNanos(20000));
        handelAppsUpdatePopUp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        killAndRelaunchApp("com.india.guruplay");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        for (int i = 0; i <= 4; i++) {
            scrollDown();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("licenseIconsRecyclerView")));
        Assert.assertTrue(driver.findElement(By.id("licenseIconsRecyclerView")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("adultOnlyTextView")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("gamblingAwareImageView")).isDisplayed());
        driver.findElement(By.id("gamblingAwareImageView")).click();
        String GamblingAware = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.chrome:id/url_bar"))).getText();
        Assert.assertEquals(GamblingAware, "begambleaware.org");
        driver.findElement(new MobileBy.ByAccessibilityId("Close tab")).click();
        Assert.assertTrue(driver.findElement(By.id("gamblingTherapyImageView")).isDisplayed());
        driver.findElement(By.id("gamblingTherapyImageView")).click();
        String GamblingTherapy = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.chrome:id/url_bar"))).getText();
        Assert.assertEquals(GamblingTherapy, "gamblingtherapy.org");
        driver.findElement(new MobileBy.ByAccessibilityId("Close tab")).click();
        Assert.assertTrue(driver.findElement(By.id("licenseTextView")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("paymentIconsRecyclerView")).isDisplayed());
        swipeLeft(1020, 1898, 50, 1898);
    }

    @Test(groups = {"StagingSmoke"})
    public void MyAccountBalanceSectionOverview() {
        wait = new WebDriverWait(driver, Duration.ofNanos(20000));
        handelAppsUpdatePopUp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        killAndRelaunchApp("com.india.guruplay");
        Login();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAccount"))).isDisplayed());
        driver.findElement(By.id("headerAccount")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("itemLogo"))).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("viewPager"))).isDisplayed());
        driver.findElement(By.id("headerAccount")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("itemClose"))).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("viewPager"))).isDisplayed());
        driver.findElement(By.id("headerAccount")).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("itemBalanceBreakDownContainer"))).isDisplayed());
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("itemWelcomeText"))).isDisplayed());
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("balanceText"))).isDisplayed());
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("depositButton"))).isDisplayed());
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sportBonusAmountText"))).isDisplayed());
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("casinoBonusAmountText"))).isDisplayed());
        if (isPresent("freeSpinBonusAmountText")) {
            Assert.assertTrue(isPresent("freeSpinBonusAmountText"));
        } else {
            Assert.assertFalse(isPresent("freeSpinBonusAmountText"));
        }
        if (isPresent("freeBetsBonusAmountText")) {
            Assert.assertTrue(isPresent("freeBetsBonusAmountText"));
        } else {
            Assert.assertFalse(isPresent("freeBetsBonusAmountText"));
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("depositButton"))).click();
        driver.context(webContext);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-uat='deposit-Amount *-field']")))
                .isDisplayed());
        driver.context(nativeContext);
        driver.findElement(By.id("fragmentWebDepositCloseButton"))
                .click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAccount"))).isDisplayed());
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (isPresent("headerAccount")) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Element is Displayed!\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Element is not Displayed!\"}}");
        }
    }

    @Test(groups = {"StagingSmoke"})
    public void MyAccountGridItemsOverview() {
        wait = new WebDriverWait(driver, Duration.ofNanos(20000));
        handelAppsUpdatePopUp();
        killAndRelaunchApp("com.india.guruplay");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        Login();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAccount"))).isDisplayed());
        driver.findElement(By.id("headerAccount")).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountMenuGridItems"))).isDisplayed());
        WebElement accountGridSection = driver.findElement(By.id("accountMenuGridItems"));
        List<WebElement> accountGridElements = accountGridSection.findElements(By.className("android.widget.RelativeLayout"));
        System.out.println(accountGridElements.size());
        for (int i = 0; i <= accountGridElements.size() - 1; i++) {
            Assert.assertTrue(accountGridElements.get(i).findElement(By.className("android.widget.ImageView")).isDisplayed());
            System.out.println(i);
        }
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (isPresent(String.valueOf(accountGridElements.get(8).findElement(By.className("android.widget.ImageView"))))) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Element is Displayed!\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Element is not Displayed!\"}}");
        }
    }

    @Test(groups = {"StagingSmoke"})
    public void MyAccountNonGridItemsOverview() {
        wait = new WebDriverWait(driver, Duration.ofNanos(20000));
        handelAppsUpdatePopUp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        killAndRelaunchApp("com.india.guruplay");
        Login();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAccount"))).isDisplayed());
        driver.findElement(By.id("headerAccount")).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountMenuNonGridItems"))).isDisplayed());
        WebElement accountGridSection = driver.findElement(By.id("accountMenuNonGridItems"));
        List<WebElement> accountGridElements = accountGridSection.findElements(By.className("android.widget.FrameLayout"));
        System.out.println(accountGridElements.size());
        for (int i = 0; i <= accountGridElements.size() - 1; i++) {
            Assert.assertTrue(accountGridElements.get(i).findElement(By.className("android.widget.TextView")).isDisplayed());
            System.out.println(i);
        }
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (isPresent(String.valueOf(accountGridElements.get(2).findElement(By.className("android.widget.TextView"))))) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Element is Displayed!\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Element is not Displayed!\"}}");
        }
    }

    @Test(groups = {"StagingSmoke"})
    public void MyAccountGridItemsNavigation() {
        wait = new WebDriverWait(driver, Duration.ofNanos(20000));
        handelAppsUpdatePopUp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        killAndRelaunchApp("com.india.guruplay");
        Login();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAccount"))).isDisplayed());
        driver.findElement(By.id("headerAccount")).click();
        WebElement accountGridSection = driver.findElement(By.id("accountMenuGridItems"));
        List<WebElement> accountGridElements = accountGridSection.findElements(By.className("android.widget.RelativeLayout"));
        accountGridElements.get(0).click();
        // need ID here
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout[3]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[1]"))).isDisplayed());
        driver.findElement(By.id("headerAccount"))
                .click();
        // think of better naming
        WebElement accountGridSection1 = driver.findElement(By.id("accountMenuGridItems"));
        List<WebElement> accountGridElements1 = accountGridSection1.findElements(By.className("android.widget.RelativeLayout"));
        accountGridElements1.get(1).click();

        driver.context(webContext);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-uat='bonus-information-tab-item-3']"))).isDisplayed());
        driver.context(nativeContext);
        driver.findElement(By.id("headerAccount"))
                .click();
        // think of better naming
        WebElement accountGridSection2 = driver.findElement(By.id("accountMenuGridItems"));
        List<WebElement> accountGridElements2 = accountGridSection2.findElements(By.className("android.widget.RelativeLayout"));
        accountGridElements2.get(6).click();

        driver.context(webContext);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-uat='account-verification-document-type-dropdown']"))).isDisplayed());
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (isPresent(String.valueOf(By.xpath("//*[@data-uat='account-verification-document-type-dropdown']")))) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Element is Displayed!\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Element is not Displayed!\"}}");
        }
    }

    @Test(groups = {"StagingSmoke"})
    public void MultipleTabsPagesNavigation() {
        wait = new WebDriverWait(driver, Duration.ofNanos(20000));
        handelAppsUpdatePopUp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        killAndRelaunchApp("com.india.guruplay");
        Login();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAccount")));
        driver.findElement(new MobileBy.ByAccessibilityId("Offers")).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subNavigation"))).isDisplayed());
        WebElement offersSubNavSection = driver.findElement(By.id("subNavigation"));
        List<WebElement> offersSubNavElements = offersSubNavSection.findElements(By.className("android.view.ViewGroup"));
        for (int i = 3; i >= 0; i--) {
            offersSubNavElements.get(i).click();
            Assert.assertTrue(offersSubNavElements.get(i).isSelected());
        }
        driver.findElement(By.id("headerAccount")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountMenuGridItems")));
        WebElement accountGridSection = driver.findElement(By.id("accountMenuGridItems"));
        List<WebElement> accountGridElements = accountGridSection.findElements(By.className("android.widget.RelativeLayout"));
        accountGridElements.get(1).click();
        driver.context(webContext);
        String CurrentBonusesSelected = driver.findElement(By.xpath("//*[@data-uat='bonus-information-tab-item-3']/button[1]")).getAttribute("aria-selected");
        Assert.assertEquals(CurrentBonusesSelected, "true");
        driver.context(nativeContext);
        driver.findElement(new MobileBy.ByAccessibilityId("Free Bets")).click();
        driver.context(webContext);
        String FreeBetsSelected = driver.findElement(By.xpath("//*[@data-uat='bonus-information-tab-item-1']/button[1]")).getAttribute("aria-selected");
        Assert.assertEquals(FreeBetsSelected, "true");
        driver.context(nativeContext);
        driver.findElement(new MobileBy.ByAccessibilityId("Free Spins")).click();
        driver.context(webContext);
        String FreeSpinsSelected = driver.findElement(By.xpath("//*[@data-uat='bonus-information-tab-item-2']/button[1]")).getAttribute("aria-selected");
        Assert.assertEquals(FreeSpinsSelected, "true");
        driver.context(nativeContext);
        driver.findElement(new MobileBy.ByAccessibilityId("Bonus History")).click();
        driver.context(webContext);
        String BonusHistorySelected = driver.findElement(By.xpath("//*[@data-uat='bonus-information-tab-item-4']/button[1]")).getAttribute("aria-selected");
        Assert.assertEquals(BonusHistorySelected, "true");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (BonusHistorySelected.equals("true")) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Element is Selected!\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Element is not Selected!\"}}");
        }
    }

    @Test(groups = {"StagingSmoke"})
    public void SocialLinksVerification() {
        wait = new WebDriverWait(driver, Duration.ofNanos(20000));
        handelAppsUpdatePopUp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerLogin")));
        killAndRelaunchApp("com.india.guruplay");
        Login();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerAccount"))).isDisplayed());
        scrollDown();
        scrollDown();
        WebElement socialIconsSection = driver.findElement(By.id("socialIcons"));
        List<WebElement> socialIconsElements = socialIconsSection.findElements(By.className("android.widget.ImageView"));
        socialIconsElements.get(0).click();
        waitForElementToAppear("com.android.chrome:id/toolbar");
        waitForElementToAppear("com.android.chrome:id/title_bar");
        String FaceBookURL = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.chrome:id/url_bar"))).getText();
        Assert.assertEquals(FaceBookURL, "m.facebook.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(new MobileBy.ByAccessibilityId("Close tab")));
        driver.findElement(new MobileBy.ByAccessibilityId("Close tab")).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("socialIcons"))).isDisplayed());
        WebElement socialIconsSection1 = driver.findElement(By.id("socialIcons"));
        List<WebElement> socialIconsElements1 = socialIconsSection1.findElements(By.className("android.widget.ImageView"));
        socialIconsElements1.get(1).click();
        waitForElementToAppear("com.android.chrome:id/title_bar");
        String TwitterURL = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.chrome:id/url_bar"))).getText();
        Assert.assertEquals(TwitterURL, "mobile.twitter.com");
        driver.findElement(new MobileBy.ByAccessibilityId("Close tab")).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("socialIcons"))).isDisplayed());
//        scrollUp();
//        Logout();
//        scrollDown();
//        scrollDown();
        WebElement socialIconsSection2 = driver.findElement(By.id("socialIcons"));
        List<WebElement> socialIconsElements2 = socialIconsSection2.findElements(By.className("android.widget.ImageView"));
        socialIconsElements2.get(2).click();
        waitForElementToAppear("com.android.chrome:id/title_bar");
        String TelegramURL = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.chrome:id/url_bar"))).getText();
        Assert.assertEquals(TelegramURL, "t.me");
        driver.findElement(new MobileBy.ByAccessibilityId("Close tab")).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("socialIcons"))).isDisplayed());
        WebElement socialIconsSection3 = driver.findElement(By.id("socialIcons"));
        List<WebElement> socialIconsElements3 = socialIconsSection3.findElements(By.className("android.widget.ImageView"));
        socialIconsElements3.get(3).click();
        waitForElementToAppear("com.android.chrome:id/title_bar");
        String InstagramURL = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.chrome:id/url_bar"))).getText();
        Assert.assertEquals(InstagramURL, "instagram.com");
        driver.findElement(new MobileBy.ByAccessibilityId("Close tab")).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("socialIcons"))).isDisplayed());
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (isPresent("socialIcons")) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Element is Displayed!\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Element is not Displayed!\"}}");
        }
    }

    @AfterTest
    public void tearDown() throws Exception {
            if (driver != null) {
                driver.quit();
            }
        }
    }