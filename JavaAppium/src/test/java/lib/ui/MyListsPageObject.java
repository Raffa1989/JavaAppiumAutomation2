package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            ARTICLE_ABOUT_JAVA_SCRIPT,
            PREVIEW_OVERFLOW_BUTTON,
            BUTTON_ADD_TO_READING_LIST,
            FOLDER_FOR_SAVED_ARTICLES,
            ELEMENT_JAVA_SCRIPT,
            NEGATIVE_BUTTON,
            LINK_JAVA_SCRIPT,
            REMOVE_SAVED_BUTTON_JAVA;


    @Step("Get folder xpath by name")
    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    @Step("Get saved article xpath by title")
    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    @Step("Get remove button by title)")
    private static String getRemoveButtonByTitle(String article_title) {
        return REMOVE_SAVED_BUTTON_JAVA.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }


    @Step("Opening folder by name")
    public void openFolderByName(String name_of_folder) {
        this.waitForElementAndClick(REMOVE_SAVED_BUTTON_JAVA, "Cannot find remove saved button", 15);
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.swipeUpToFindElement(folder_name_xpath, "Cannot find folder by name " + name_of_folder, 20);
    }

    @Step("Click on the star Java")
    public void clickOnTheStarJava() {
        this.waitForElementAndClick(REMOVE_SAVED_BUTTON_JAVA, "Cannot find and click star button", 15);
    }

    @Step("Waiting for article to appear by title")
    public void waitFoArticleToAppearByTitle(String article_title) {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find saved article by title  " + article_title, 5);

    }

    @Step("Waiting for article to disappear by title")
    public void waitFoArticleToDisappearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved article still present with title " + article_title, 15);

    }

    @Step("Swiping and delete the article")
    public void swipeByArticleToDelete(String article_title) {
        this.waitFoArticleToAppearByTitle(article_title);
        String article_xpath = getFolderXpathByName(article_title);

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(article_xpath, "Cannot find saved article");
        } else {
            driver.navigate().refresh();
            this.waitFoArticleToDisappearByTitle(article_title);
        }

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }
    }

    @Step("Adding article about JavaScript")
    public void addArticleAboutJavaScript(String name_of_folder) {
        this.waitForElementAndClick(ARTICLE_ABOUT_JAVA_SCRIPT, "Cannot find text JavaScript", 5);
        this.waitForElementAndClick(PREVIEW_OVERFLOW_BUTTON, "Cannot find overflow button", 5);
        this.waitForElementAndClick(BUTTON_ADD_TO_READING_LIST, "Cannot find button to add article", 5);
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(folder_name_xpath, "Cannot find " + name_of_folder, 5
        );
    }

    @Step("Assert stayed article about JavaScript (for Android and iOS")
    public void assertStayedArticleAboutJavaScript() {
        this.waitForElementPresent(ELEMENT_JAVA_SCRIPT, "Cannot find article about JavaScript", 0);
        this.waitForElementAndClick(ELEMENT_JAVA_SCRIPT, "Cannot find title JavaScript", 5);
        this.waitForElementPresent(ARTICLE_ABOUT_JAVA_SCRIPT, "Cannot find article title about JavaScript", 0);
    }

    @Step("Assert stayed article about JavaScript (for Mobile Web")
    public void assertStayedArticleAboutJavaScriptForWebMobile() {
        this.waitForElementPresent(LINK_JAVA_SCRIPT, "Cannot find article about JavaScript", 0);
    }
}
