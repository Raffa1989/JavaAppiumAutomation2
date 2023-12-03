package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTION_ADD_TO_LIST = "css:#ca-watch>span.minerva-icon.minerva-icon--star-base20";
        OPTION_REMOVE_FROM_MY_LIST_BUTTON = "css:#ca-watch>span.minerva-icon.minerva-icon--unStar-progressive";
    }

    public MWArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}


