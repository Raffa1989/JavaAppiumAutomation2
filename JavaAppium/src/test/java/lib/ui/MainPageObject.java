package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import lib.Platform;
import java.time.Duration;

import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver)
    {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {

        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }


    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = this.waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds) {
        WebElement element = this.waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {

        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    //Ex2: Создание метода (первая часть задания)
    // 19.01 Возможно  допущена ошибка
    public WebElement assertElementHasText(String locator, String expected_text, String error_message, long timeoutUnSeconds) {

        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutUnSeconds);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        String actualText = element.getAttribute("text");

        if (!actualText.equals(expected_text)) {
            throw new AssertionError(error_message);
        }
        return element;
    }

    /*
    public void assertElementNotPresent(String locator, String error_message) {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 2) {
            String default_message = "An element '" + locator + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }
     */

    /*
    // ????????
    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(PointOption.point(x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x, end_y))
                .release()
                .perform();
    }
     */


    public void swipeUp(int timeOfSwipe) {

        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);

            action
                    .press(x, start_y)
                    .waitAction(Duration.ofDays(timeOfSwipe))
                    .moveTo(x, end_y)
                    .release()
                    .perform();
        } else {
            System.out.println("Method swipeUp() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void scrollWebPageUp() {
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0, 250)");
        } else {
            System.out.println("Method scrollWebPageUp() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageTitleElementNotVisible(String locator, String error_message, int max_swipes) {
        int already_swipe = 0;

        WebElement element = this.waitForElementPresent(locator, error_message, 0);

        while (!this.isElementLocatedOnTheScreen(locator)) {
            scrollWebPageUp();
            ++already_swipe;
            if (already_swipe > max_swipes) {
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }
    }


    // ПОДУМАТЬ ОШИБКА возможна!!!!
    public void swipeUpToFindElement (String locator, String error_message, int max_swipes) {

        if (driver instanceof AppiumDriver) {

            By by = this.getLocatorByString(locator);
            int already_swipes = 0;
            while (driver.findElements(by).size() == 0) {

                if (already_swipes > max_swipes) {
                    waitForElementPresent(locator, "Cannot find element by swipping up. \n" + error_message, 0);
                    return;
                }

                swipeUpQuick();
                ++already_swipes;
            }
        } else {
            System.out.println("Method swipeUpToFindElement() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    /*
    // ??????
    public void swipeElementToLeft(String locator, String error_message) {
        WebElement element = waitForElementPresent(
                locator,
                error_message,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(right_x, middle_y));
        action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)));

        if (Platform.getInstance().isAndroid()) {
            action.moveTo(PointOption.point(left_x, middle_y));
        } else {
            int offset_x = (-1 * element.getSize().getWidth());
            action.moveTo(PointOption.point(offset_x, 0));
        }
    }
     */


    public void swipeElementToLeft (String locator, String error_message) {

        if (driver instanceof AppiumDriver) {
            WebElement element = waitForElementPresent(
                    locator,
                    error_message,
                    0);

            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            action.press(right_x, middle_y).perform();
            action.waitAction(Duration.ofSeconds(300));


            if (Platform.getInstance().isAndroid()) {
                action.moveTo(left_x, middle_y).perform();
            } else {
                int offset_x = (-1 * element.getSize().getWidth());
                action.moveTo(offset_x, 0).perform();
            }
        } else {
            System.out.println("Method swipeElementToLeft() does nothing for platform" + Platform.getInstance().getPlatformVar());
    }
}




    /*
    public void clickElementToTheRightUpperCorner(String locator, String error_message) {

        WebElement element = this.waitForElementPresent(locator + "/..", error_message, 5);
        int right_x = element.getLocation().getX();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        int width = element.getSize().getWidth();

        int point_to_click_x = (right_x + width) - 3;
        int point_to_click_y = middle_y;

        TouchAction action = new TouchAction(driver);
        action.tap(TapOptions.tapOptions().withPosition(PointOption.point(point_to_click_x, point_to_click_y))).perform();
    }
     */


    public void clickElementToTheRightUpperCorner (String locator, String error_message) {

        if (driver instanceof AppiumDriver) {
            WebElement element = this.waitForElementPresent(locator + "/..", error_message, 0);
            int right_x = element.getLocation().getX();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;
            int wight = element.getSize().getWidth();

            int point_to_click_x = (right_x + wight) - 3;
            int point_to_click_y = middle_y;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            action.tap(point_to_click_x, point_to_click_y).perform();
        } else {
            System.out.println("Method clickElementToTheRightUpperCorner() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }


    public int getAmountOfElements (String locator) {

        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void tryClickElementWithFewAttempts(String locator, String error_message, int amount_of_attempts) {
        int current_attempts = 0;
        boolean need_more_attempts = true;

        while (need_more_attempts) {
            try {
                this.waitForElementAndClick(locator, error_message, 1);
                need_more_attempts = false;
            } catch (Exception e) {
                if (current_attempts > amount_of_attempts) {
                    this.waitForElementAndClick(locator, error_message, 1);
                }
            }
            ++ current_attempts;
        }
    }

    public boolean isElementPresent(String locator) {
        return getAmountOfElements(locator) > 0;
    }

    // я использовала amount_of_elements > 2 так как в моей версии приложения выдается два результата
    // 'Ничего не найдено' - в русской версии и английской
    public void assertElementNotPresent(String locator, String error_message) {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 2) {
            String default_message = "An element '" + locator + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    // Ex6: Тест: assert title
    public void assertElementPresent(String locator, String error_message) {

        By by = this.getLocatorByString(locator);
        List<WebElement> elements = driver.findElements(by);
        if (elements.isEmpty()) {
            throw new AssertionError(error_message);
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator)
    {
        int element_location_by_y = this.waitForElementPresent(locator, "Cannot find element by locator", 0).getLocation().getY();
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            Object js_result = JSExecutor.executeScript("return window.pageYOffset");
            element_location_by_y -= Integer.parseInt(js_result.toString());
        }
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }

    public void swipeUpTitleElementAppear(String locator, String error_message, int max_swipes)
    {
        int already_swiped = 0;

        while (!this.isElementLocatedOnTheScreen(locator))
        {
            if(already_swiped > max_swipes){
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

/*    By getLocatorByString(String locator_with_type)
    {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);

        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else if (by_type.equals("css")) {
            return By.cssSelector(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
        }
    } */

    public By getLocatorByString(String locator_with_type) {
        try {
            String[] exploded_locator = locator_with_type.split(":", 2);

            if (exploded_locator.length != 2) {
                throw new IllegalArgumentException("Invalid locator format: " + locator_with_type);
            }

            String by_type = exploded_locator[0];
            String locator = exploded_locator[1];

            if (by_type.equals("xpath")) {
                return By.xpath(locator);
            } else if (by_type.equals("id")) {
                return By.id(locator);
            } else if (by_type.equals("css")) {
                return By.cssSelector(locator);
            } else {
                throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
            }
        } catch (Exception e) {
            System.out.println("Error in getLocatorByString. Locator: " + locator_with_type);
            e.printStackTrace();
            throw e;
        }
    }

}

