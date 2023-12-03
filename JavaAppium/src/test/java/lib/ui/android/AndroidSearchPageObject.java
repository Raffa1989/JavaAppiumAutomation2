package lib.ui.android;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        PASS_ELEMENT = "xpath://*[contains(@text, 'Пропустить')]";
        SEARCH_INIT_ELEMENT = "xpath://android.widget.TextView[@text='Поиск по Википедии']";
        SEARCH_INPUT = "xpath://android.widget.EditText[@text='Поиск по Википедии']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(@text='{SUBSTRING}')]";
        SEARCH_CANCEL_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Перейти вверх']";
        LANGUAGE_ENGLISH_BUTTON = "xpath://android.widget.TextView[@text='ENGLISH']";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@class='android.view.ViewGroup']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://android.widget.TextView[@text='Ничего не найдено']";
        SEARCH_CONTAINS = "xpath://*[contains(@text, 'Поиск по Википедии')]";
        PAGE_LIST_ITEM_TITLE = "xpath://android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title']";
        SEARCH_INPUT_TEXT = "id:org.wikipedia:id/search_src_text";
    }

    public AndroidSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
