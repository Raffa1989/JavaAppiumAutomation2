package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    public static final String
            FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']",
            ARTICLE_ABOUT_JAVA_SCRIPT = "//android.widget.TextView[@text='JavaScript']",
            PREVIEW_OVERFLOW_BUTTON = "org.wikipedia:id/link_preview_overflow_button",
            BUTTON_ADD_TO_READING_LIST = "//*[@text='Добавить в список для чтения']",
            FOLDER_FOR_SAVED_ARTICLES = "//android.widget.TextView[@text='{FOLDER}']",
            ELEMENT_JAVA_SCRIPT = "//*[@text='JavaScript']";


    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    /* тут я еще покумекаю
    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
     */


    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.swipeUpToFindElement(By.xpath(folder_name_xpath), "Cannot find folder by name " + name_of_folder, 20);
    }

    public void waitFoArticleToAppearByTitle(String article_title) {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent(By.xpath(article_xpath), "Cannot find saved article by title  " + article_title, 15);

    }

    public void waitFoArticleToDisappearByTitle(String article_title) {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(By.xpath(article_xpath), "Saved article still present with title " + article_title, 15);

    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitFoArticleToAppearByTitle(article_title);
        String article_xpath = getFolderXpathByName(article_title);
        this.swipeElementToLeft(By.xpath(article_xpath), "Cannot find saved article " + article_title);
        this.waitFoArticleToDisappearByTitle(article_title);
    }

    public void addArticleAboutJavaScript(String name_of_folder) {
        this.waitForElementAndClick(By.xpath(ARTICLE_ABOUT_JAVA_SCRIPT), "Cannot find text JavaScript", 5);
        this.waitForElementAndClick(By.id(PREVIEW_OVERFLOW_BUTTON), "Cannot find overflow button", 5);
        this.waitForElementAndClick(By.xpath(BUTTON_ADD_TO_READING_LIST), "Cannot find button to add article", 5);
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(By.xpath(folder_name_xpath), "Cannot find " + name_of_folder, 5
        );
    }

    public void assertStayedArticleAboutJavaScript() {
        this.waitForElementPresent(By.xpath(ELEMENT_JAVA_SCRIPT), "Cannot find article about JavaScript", 5);
        this.waitForElementAndClick(By.xpath(ELEMENT_JAVA_SCRIPT), "Cannot find title JavaScript", 5);
        this.waitForElementPresent(By.xpath(ARTICLE_ABOUT_JAVA_SCRIPT), "Cannot find article title about JavaScript", 15);
    }
}
