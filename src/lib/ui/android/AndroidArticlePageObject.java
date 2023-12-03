package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:pcs-edit-section-title-description";
        FOOTER_ELEMENT = "xpath://*[@text='View article in browser']";
        SAVE_BUTTON = "xpath://android.widget.TextView[@text='Сохранить']";
        OPTION_ADD_TO_LIST = "xpath://android.widget.Button[@text='Добавить в список']";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "id:android:id/button1";
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Перейти вверх']";
    }

    public AndroidArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
