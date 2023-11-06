package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{

    private static final String
            MY_LIST_LINK = "org.wikipedia:id/nav_tab_reading_lists",
            OPTION_CLICK_ITEM_TITLE = "org.wikipedia:id/item_title",
            ALL_LIST = "//android.widget.Button[@text='Посмотреть список']";

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyList()
    {
        this.waitForElementAndClick(
                By.id(MY_LIST_LINK),
                "Cannot find reading lists",
                5
        );
    }

    public void clickItemTitle()
    {
        this.waitForElementAndClick(
                By.id(OPTION_CLICK_ITEM_TITLE),
                "Cannot find item title",
                5
        );
    }

    public void lookAllList()
    {
        this.waitForElementAndClick(
                By.xpath(ALL_LIST),
                "Cannot find button see list",
                5
        );
    }
}
