package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]";
        REMOVE_SAVED_BUTTON_JAVA = "xpath://a[contains(@href, 'Java_(programming_language)') and contains(@class, 'watched')]";
        FOLDER_BY_NAME_TPL =  "xpath://a[@href='/wiki/Java_(programming_language)']";
    }

    public MWMyListsPageObject (RemoteWebDriver driver)
    {
        super(driver);
    }
}
