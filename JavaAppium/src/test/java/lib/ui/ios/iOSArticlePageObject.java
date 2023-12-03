package lib.ui.ios;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        SAVE_BUTTON = "xpath://android.widget.TextView[@text='Сохранить']"; // оставила на всякий случай
        OPTION_ADD_TO_LIST = "id:Save for later";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input"; // оставила на всякий случай
        MY_LIST_OK_BUTTON = "id:android:id/button1"; // оставила на всякий случай
        CLOSE_ARTICLE_BUTTON = "id:Back";
    }

    public iOSArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
