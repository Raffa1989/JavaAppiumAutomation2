package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class AndroidMyListsPageObject extends MyListsPageObject {

    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
        ARTICLE_ABOUT_JAVA_SCRIPT = "xpath://android.widget.TextView[@text='JavaScript']";
        PREVIEW_OVERFLOW_BUTTON = "id:org.wikipedia:id/link_preview_overflow_button";
        BUTTON_ADD_TO_READING_LIST = "xpath://*[@text='Добавить в список для чтения']";
        FOLDER_FOR_SAVED_ARTICLES = "xpath://android.widget.TextView[@text='{FOLDER}']";
        ELEMENT_JAVA_SCRIPT = "xpath://*[@text='JavaScript']";
    }

    public AndroidMyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
