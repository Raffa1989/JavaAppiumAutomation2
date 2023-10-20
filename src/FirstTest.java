import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


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
        capabilities.setCapability("app","/Users/konevat/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void firstTest() throws InterruptedException {

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Пропустить')]"),
                "Cannot find 'Пропустить'",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Поиск по Википедии']"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//android.widget.EditText[@text='Поиск по Википедии']"),
                "Java",
                "Cannot find search input 2",
                10
        );

        this.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='язык программирования']"),
                "Cannot find 'язык программирования topic searching by 'Java'",
                15
        );

        Thread.sleep(3000);
    }

    @Test
    public void testCancelSearch() throws InterruptedException {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Пропустить')]"),
                "Cannot find 'Пропустить'",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Пропустить' 2",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//android.widget.EditText[@text='Поиск по Википедии']"),
                "Java",
                "Cannot find search input 2",
                10
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );

        // я использовала Xpath так как в приложении отсутсвует крестик, есть стрелочка, возвращающая на
        // предыдущий экран. у нее resource-id отсутствует. на главном экране эта кнопка также отсутствует
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Перейти вверх']"),
                "Cannot find 'Перейти вверх'",
                5
        );

        Thread.sleep(5000);

        waitForElementNotPresent(
                By.xpath("//android.widget.ImageButton[@content-desc='Перейти вверх']"),
                "'Перейти вверх' is still present on the page",
                5
        );
    }

    @Test
    public void testCompareArticleTitle() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Пропустить')]"),
                "Cannot find 'Пропустить'",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Поиск по Википедии']"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//android.widget.EditText[@text='Поиск по Википедии']"),
                "Java",
                "Cannot find search input 2",
                10
        );

        // название статьи состоит из двух строк. одна "Java", вторая "язык программирования"
        // взяла для теста вторую строку
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='язык программирования']"),
                "Cannot find search input",
                5
        );

        WebElement title_element = waitForElementPresent(
                By.id("pcs-edit-section-title-description"),
                "Cannot find article title",
                15
        );

        String article_title = title_element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title",
                "язык программирования",
                article_title
        );
    }

     //Ex2: Создание метода (вторая часть задания)
     @Test
     public void testFieldContainsText() {
         waitForElementAndClick(
                 By.xpath("//*[contains(@text, 'Пропустить')]"),
                 "Cannot find 'Пропустить'",
                 5
         );

         waitForElementAndClick(
                 By.xpath("//android.widget.TextView[@text='Поиск по Википедии']"),
                 "Cannot find search input",
                 10
         );

         assertElementHasText(
                 By.xpath("//*[contains(@text, 'Поиск по Википедии')]"),
                 "Поиск по Википедии",
                 "Cannot find 'Поиск по Википедии'",
                 10
         );

     }

     //Ex3: Тест: отмена поиска
     @Test
     public void testSearchDisappeared() {
         waitForElementAndClick(
                 By.xpath("//*[contains(@text, 'Пропустить')]"),
                 "Cannot find 'Пропустить'",
                 5
         );

         waitForElementAndClick(
                 By.xpath("//android.widget.TextView[@text='Поиск по Википедии']"),
                 "Cannot find search input",
                 10
         );

         waitForElementAndSendKeys(
                 By.xpath("//android.widget.EditText[@text='Поиск по Википедии']"),
                 "Moskow",
                 "Cannot find 'Moskow'",
                 10
         );

         waitForElementPresent(
                 By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title']"),
                 "Cannot find articles",
                 5
         );

         List<WebElement> searchResults = driver.findElements(By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title']"));
         Assert.assertTrue("Less than two articles found", searchResults.size() >= 2);


         waitForElementAndClick(
                 By.id("org.wikipedia:id/search_src_text"),
                 "Cannot find search input",
                 10
         );

         waitForElementAndClear(
                 By.id("org.wikipedia:id/search_src_text"),
                 "Cannot find search field",
                 5
         );

         waitForElementNotPresent(
                 By.id("org.wikipedia:id/page_list_item_title"),
                 "Articles is still present on the page",
                 15
         );
     }

     //Ex4*: Тест: проверка слов в поиске
     @Test
     public void testCheckingWordsInSearch() {
         waitForElementAndClick(
                 By.xpath("//*[contains(@text, 'Пропустить')]"),
                 "Cannot find 'Пропустить'",
                 5
         );

         waitForElementAndClick(
                 By.xpath("//android.widget.TextView[@text='Поиск по Википедии']"),
                 "Cannot find search input",
                 10
         );

         waitForElementAndSendKeys(
                 By.xpath("//android.widget.EditText[@text='Поиск по Википедии']"),
                 "Java",
                 "Cannot find 'Java'",
                 10
         );

         List<WebElement> searchResults = driver.findElements(By.id("page_list_item_title"));
         for (WebElement result : searchResults){
             String resultText = result.getText();
             Assert.assertTrue("Слово 'Java' не найдено в результатах поиска", resultText.contains("Java"));
         }


     }



    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementNotPresent(By by, String error_message)
    {
        return this.waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = this.waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = this.waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    //Ex2: Создание метода (первая часть задания)
    private WebElement assertElementHasText(By by, String expected_text, String error_message, long timeoutUnSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutUnSeconds);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        String actualText = element.getAttribute("text");

        if (!actualText.equals(expected_text)) {
            throw new AssertionError(error_message);
        }
        return element;
    }
}

