import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","10");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/konevat/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia_50454_apps.evozi.com.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void firstTest() {
        WebElement element_to_init_search = driver.findElementByXPath("//*[contains(@text, 'Пропустить')]");
        element_to_init_search.click();

        //Thread.sleep(5000);

        //WebElement element_to_init_search_1 = driver.findElementByXPath("//*[contains(@text, 'Поиск по Википедии')]");
        //element_to_init_search_1.click();

        WebElement element_to_enter_search_line = waitForElementPresentByXpath(
                "//*[contains(@text, 'Поиск по Википедии')]",
                "Cannot find search input",
                5
        );

        element_to_enter_search_line.sendKeys("Языки программирования");
    }

    private WebElement waitForElementPresentByXpath (String xpath, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
