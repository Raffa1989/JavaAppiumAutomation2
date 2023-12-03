package lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            PASS_ELEMENT,
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_CANCEL_BUTTON,
            LANGUAGE_ENGLISH_BUTTON,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_CONTAINS,
            PAGE_LIST_ITEM_TITLE,
            SEARCH_INPUT_TEXT;


    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /* Templates methods */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* Templates methods */


    public void initSearchInput() {
        //this.waitForElementAndClick(PASS_ELEMENT, "Cannot find 'Пропустить'", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element", 0);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find search input 2", 10);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring, 0);
    }

    public void waitForCancelButtonAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find 'Перейти вверх'", 0);
    }

    public void waitForCancelButtonDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "'Перейти вверх' is still present on the page", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cansel button", 5);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    public void waitLanguageButtonAndClick() {
        this.waitForElementAndClick(LANGUAGE_ENGLISH_BUTTON, "Cannot button ENGLISH", 5);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request",
                0);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 0);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not find any results");
    }

    // Возможно ОШИБКА!!!
    public void testSearchInputPlaceholderText() {
        String searchInput = SEARCH_CONTAINS;

        assertElementHasText(
                searchInput,
                "Поиск по Википедии",
                "Search input does not have the expected placeholder text",
                5
        );
    }

    public void waitForPageListItemTitlePresent() {
        this.waitForElementPresent(PAGE_LIST_ITEM_TITLE, "Cannot find articles", 0);
    }

    //ОШИБКА возможно
    public List<WebElement> getSearchResults() {

        By by = this.getLocatorByString(PAGE_LIST_ITEM_TITLE);
        List<WebElement> search_results = driver.findElements(by);
        return search_results;
    }

    public void clickElementSearchInputText() {
        this.waitForElementAndClick(SEARCH_INPUT_TEXT, "Cannot find search input", 10);
    }

    public void clearElementSearchInputText() {
        this.waitForElementAndClear(SEARCH_INPUT_TEXT, "Cannot find search field", 10);
    }

    public void waitForPageListItemTitleNotPresent() {
        this.waitForElementNotPresent(PAGE_LIST_ITEM_TITLE, "Articles is still present on the page", 5);
    }

    public void waitForSearchResultAndClick(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find search input " + substring, 5);
    }
}



