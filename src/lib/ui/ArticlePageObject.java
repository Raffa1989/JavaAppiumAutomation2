package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
        TITLE = "pcs-edit-section-title-description",
        FOOTER_ELEMENT = "//*[@text='View article in browser']",
        SAVE_BUTTON = "//android.widget.TextView[@text='Сохранить']",
        OPTION_ADD_TO_LIST = "//android.widget.Button[@text='Добавить в список']",
        MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "android:id/button1",
        CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Перейти вверх']";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of the article",
                20
        );
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(SAVE_BUTTON),
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                By.xpath(OPTION_ADD_TO_LIST),
                "Cannot find button to add article",
                5
        );

        this.waitForElementAndClick(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find input field to set name",
                5
        );

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find input field to set name 2",
                5
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text into articles folder into",
                5
        );

        this.waitForElementAndClick(
                By.id(MY_LIST_OK_BUTTON),
                "Cannot button OK",
                5
        );
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot find 'Перейти вверх'",
                5
        );
    }

    public void assertArticleTitleWithoutWait()
    {
        this.assertElementPresent(
                By.id(TITLE),
                "Cannot find article title without wait"
        );
    }
}