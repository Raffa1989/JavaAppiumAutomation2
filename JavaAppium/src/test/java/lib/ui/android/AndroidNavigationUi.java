package lib.ui.android;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUi extends NavigationUI {

    static {
        ALL_LIST = "xpath://android.widget.Button[@text='Посмотреть список']";
        MY_LIST_LINK = "id:org.wikipedia:id/nav_tab_reading_lists";
        OPTION_CLICK_ITEM_TITLE = "id:org.wikipedia:id/item_title";
    }

    public AndroidNavigationUi(RemoteWebDriver driver)
    {
        super(driver);
    }
}