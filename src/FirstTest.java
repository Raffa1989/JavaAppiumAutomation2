import com.sun.jna.platform.win32.WinDef;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/konevat/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    // Ex7*: Поворот экрана
    public void tearDown() {
        driver.rotate(ScreenOrientation.PORTRAIT); // Восстанавливаем экран в портретное положение
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
        for (WebElement result : searchResults) {
            String resultText = result.getText();
            Assert.assertTrue("Слово 'Java' не найдено в результатах поиска", resultText.contains("Java"));
        }
    }

    @Test
    public void testSwipeArticle() {
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

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='ENGLISH']"),
                "Cannot button ENGLISH",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//android.widget.EditText[@text='Поиск по Википедии']"),
                "Appium",
                "Cannot find search input 2",
                10
        );


        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find search input",
                5
        );

        swipeUpToFindElement(
                By.xpath("//*[@text='View article in browser']"),
                "Cannot find the end of the article",
                20
        );
    }

    // получилось довольно много действий, так как приложение уже не такое как в уроке
    @Test
    public void saveFirstArticleToMyList() {
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

        waitForElementPresent(
                By.id("pcs-edit-section-title-description"),
                "Cannot find article title",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Сохранить']"),
                "Cannot find button to open article options",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.Button[@text='Добавить в список']"),
                "Cannot find button to add article",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input field to set name",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input field to set name 2",
                5
        );

        String name_of_folder = "Learning programming";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder into",
                5
        );

        waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot button OK",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Перейти вверх']"),
                "Cannot find 'Перейти вверх'",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Перейти вверх']"),
                "Cannot find 'Перейти вверх' 2",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "Cannot find reading lists",
                5
        );

        swipeUpToFindElement(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find the list",
                20
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/item_title"),
                "Cannot find item title",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='Java']"),
                "Cannot find saved article"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='Java']"),
                "Cannot find deleted safe article",
                5
        );

    }

    // Ex5: Тест: сохранение двух статей
    @Test
    public void saveTwoArticlesToMyList() {
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

        waitForElementPresent(
                By.id("pcs-edit-section-title-description"),
                "Cannot find article title",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Сохранить']"),
                "Cannot find button to open article options",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.Button[@text='Добавить в список']"),
                "Cannot find button to add article",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input field to set name",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input field to set name 2",
                5
        );

        String name_of_folder_2 = "Learning programming";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder_2,
                "Cannot put text into articles folder into",
                5
        );

        waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot button OK",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='JavaScript']"),
                "Cannot find text JavaScript",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/link_preview_overflow_button"),
                "Cannot find overflow button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Добавить в список для чтения']"),
                "Cannot find button to add article",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='" + name_of_folder_2 + "']"),
                "Cannot find list Learning programming",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.Button[@text='Посмотреть список']"),
                "Cannot find button see list",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='Java']"),
                "Cannot find saved article"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='Java']"),
                "Cannot find deleted safe article",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@text='JavaScript']"),
                "Cannot find article about JavaScript",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='JavaScript']"),
                "Cannot find title JavaScript",
                5
        );

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='JavaScript']"),
                "Cannot find article title",
                15
        );
    }

    @Test
    public void testAmountOfNotEmptySearch() {
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

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='ENGLISH']"),
                "Cannot button ENGLISH",
                5
        );

        String search_line = "Linkin Park Diskography";
        waitForElementAndSendKeys(
                By.xpath("//android.widget.EditText[@text='Поиск по Википедии']"),
                search_line,
                "Cannot find search input 2",
                10
        );

        // в новой версии приложения нет контейнеров, вмето них class='android.view.ViewGroup'
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@class='android.view.ViewGroup']";
        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " + search_line,
                15
        );

        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result_locator)
        );

        Assert.assertTrue(
                "We found a few results!",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
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

        String search_line = "fwplllk";
        waitForElementAndSendKeys(
                By.xpath("//android.widget.EditText[@text='Поиск по Википедии']"),
                search_line,
                "Cannot find search input 2",
                10
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@class='android.view.ViewGroup']";
        String empty_result_label = "//android.widget.TextView[@text='Ничего не найдено']";

        waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find result label by the request " + search_line,
                15
        );

        assertElementNotPresent(
                By.xpath(search_result_locator),
                "We've found some results by request " + search_line
        );
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
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

        String search_line = "Java";
        waitForElementAndSendKeys(
                By.xpath("//android.widget.EditText[@text='Поиск по Википедии']"),
                search_line,
                "Cannot find search input 2",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='язык программирования']"),
                "Cannot find 'язык программирования' topic searching by " + search_line,
                15
        );

        String title_before_rotation = waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find title of article before screen rotation",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find title of article after screen rotation",
                15
        );

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find title of article after second screen rotation",
                15
        );

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    /*
    @Test
    public void testCheckSearchArticleInBackground() {
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
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='язык программирования']"),
                "Cannot find search input",
                5
        );

        // driver.runAppInBackground(3);
        //driver.resetApp();

        // Сворачиваем приложение
        driver.runAppInBackground(5);


// Восстанавливаем приложение
        // driver.launchApp();

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='язык программирования']"),
                "Cannot find article after returning from background",
                5
        );
    }
     */

    // Ex6: Тест: assert title
    @Test
    public void assertTitlePresentInArticle() {
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
        // взяла для теста вторую строку. именно ее ID использовала для ассерта
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='язык программирования']"),
                "Cannot find search input",
                5
        );

        assertElementPresent(
                By.id("pcs-edit-section-title-description"),
                "Cannot find article title without wait"
        );
    }



    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }


    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = this.waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = this.waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    //Ex2: Создание метода (первая часть задания)
    private WebElement assertElementHasText(By by, String expected_text, String error_message, long timeoutUnSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutUnSeconds);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        String actualText = element.getAttribute("text");

        if (!actualText.equals(expected_text)) {
            throw new AssertionError(error_message);
        }
        return element;
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        int already_swipes = 0;
        while (driver.findElements(by).size() == 0) {

            if (already_swipes > max_swipes) {
                waitForElementPresent(by, "Cannot find element by swipping up. \n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++already_swipes;
        }
    }

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(150)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    // я использовала amount_of_elements > 2 так как в моей версии приложения выдается два результата
    // 'Ничего не найдено' - в русской версии и английской
    private void assertElementNotPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 2) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    // Ex6: Тест: assert title
    private void assertElementPresent(By by, String error_message) {
        List<WebElement> elements = driver.findElements(by);
        if (elements.isEmpty()) {
            throw new AssertionError(error_message);
        }
    }
}


