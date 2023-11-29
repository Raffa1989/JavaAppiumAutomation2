package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject{

    protected static String
            MY_LIST_LINK,
            OPTION_CLICK_ITEM_TITLE,
            ALL_LIST;

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyList()
    {
        this.waitForElementAndClick(
                MY_LIST_LINK,
                "Cannot find reading lists",
                5
        );
    }

    public void clickItemTitle()
    {
        this.waitForElementAndClick(
                OPTION_CLICK_ITEM_TITLE,
                "Cannot find item title",
                5
        );
    }

    public void lookAllList()
    {
        this.waitForElementAndClick(
                ALL_LIST,
                "Cannot find button see list",
                5
        );
    }
}
