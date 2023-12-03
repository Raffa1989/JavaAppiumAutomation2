package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        PASS_ELEMENT = "xpath://*[contains(@text, 'Пропустить')]"; // возможно, такого элемента нет. оставила без изменений
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink|contains(@name, '{SUBSTRING}')]";
        SEARCH_CANCEL_BUTTON = "id:Close";
        LANGUAGE_ENGLISH_BUTTON = "xpath://android.widget.TextView[@text='ENGLISH']"; // возможно, такого элемента нет. оставила без изменений
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No result found']";
        SEARCH_CONTAINS = "xpath://*[contains(@text, 'Поиск по Википедии')]"; // возможно, такого элемента нет. оставила без изменений
        PAGE_LIST_ITEM_TITLE = "xpath://android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title']"; // возможно, такого элемента нет. оставила без изменений
        SEARCH_INPUT_TEXT = "id:org.wikipedia:id/search_src_text"; // возможно, такого элемента нет. оставила без изменений
    }

    public iOSSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
