package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{

    protected static String
            MY_LIST_LINK,
            OPTION_CLICK_ITEM_TITLE,
            ALL_LIST,
            OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void openNavigation() {
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click navigation button", 5);
        } else {
            System.out.println("Method openNavigation() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void clickMyList()
    {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(
                    MY_LIST_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    MY_LIST_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        }
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
