package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import lib.Platform;

public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE,
            FOOTER_ELEMENT,
            SAVE_BUTTON,
            OPTION_ADD_TO_LIST,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title", 15);
    }

    public void assertArticleTitleWithoutWait() {
        this.assertElementPresent(
                TITLE,
                "Cannot find article title without wait"
        );
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public void addArticlesToMySaved()
    {
        this.waitForElementAndClick(OPTION_ADD_TO_LIST, "Cannot find to add article to rading", 5);
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot find 'Перейти вверх'",
                5
        );
    }

    public void addArticleToMyList (String name_of_folder)
    {
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                OPTION_ADD_TO_LIST,
                "Cannot find button to add article",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_NAME_INPUT,
                "Cannot find input field to set name",
                5
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input field to set name 2",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder into",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot button OK",
                5
        );
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        } else {
            this.swipeUpTitleElementAppear(
                    FOOTER_ELEMENT,
                    "annot find the end of article",
                    40
            );
        }
    }
}
