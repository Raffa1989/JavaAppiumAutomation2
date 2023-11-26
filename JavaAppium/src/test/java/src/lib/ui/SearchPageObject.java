package src.lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchPageObject extends MainPageObject {

    private static final String
            PASS_ELEMENT = "//*[contains(@text, 'Пропустить')]",
            SEARCH_INIT_ELEMENT = "//android.widget.TextView[@text='Поиск по Википедии']",
            SEARCH_INPUT = "//android.widget.EditText[@text='Поиск по Википедии']",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']",
            SEARCH_CANCEL_BUTTON = "//android.widget.ImageButton[@content-desc='Перейти вверх']",
            LANGUAGE_ENGLISH_BUTTON = "//android.widget.TextView[@text='ENGLISH']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@class='android.view.ViewGroup']",
            SEARCH_EMPTY_RESULT_ELEMENT = "//android.widget.TextView[@text='Ничего не найдено']",
            SEARCH_CONTAINS = "//*[contains(@text, 'Поиск по Википедии')]",
            PAGE_LIST_ITEM_TITLE = "//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title']",
            SEARCH_INPUT_TEXT = "org.wikipedia:id/search_src_text";


    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* Templates methods */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* Templates methods */


    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(PASS_ELEMENT), "Cannot find 'Пропустить'", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element", 5);
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find search input 2", 10);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring, 15);
    }

    public void waitForCancelButtonAppear() {
        this.waitForElementPresent(By.xpath(SEARCH_CANCEL_BUTTON), "Cannot find 'Перейти вверх'", 5);
    }

    public void waitForCancelButtonDisappear() {
        this.waitForElementNotPresent(By.xpath(SEARCH_CANCEL_BUTTON), "'Перейти вверх' is still present on the page", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(By.xpath(SEARCH_CANCEL_BUTTON), "Cannot find and click search cansel button", 5);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }

    public void waitLanguageButtonAndClick() {
        this.waitForElementAndClick(By.xpath(LANGUAGE_ENGLISH_BUTTON), "Cannot button ENGLISH", 5);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request",
                15
        );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed not find any results");
    }

    public void testSearchInputPlaceholderText() {
        By searchInput = By.xpath(SEARCH_CONTAINS);

        assertElementHasText(
                searchInput,
                "Поиск по Википедии",
                "Search input does not have the expected placeholder text",
                5
        );
    }

    public void waitForPageListItemTitlePresent() {
        this.waitForElementPresent(By.xpath(PAGE_LIST_ITEM_TITLE), "Cannot find articles", 5);
    }

    public List<WebElement> getSearchResults() {
        List<WebElement> search_results = driver.findElements(By.xpath(PAGE_LIST_ITEM_TITLE));
        return search_results;
    }

    public void clickElementSearchInputText() {
        this.waitForElementAndClick(By.id(SEARCH_INPUT_TEXT), "Cannot find search input", 10);
    }

    public void clearElementSearchInputText() {
        this.waitForElementAndClear(By.id(SEARCH_INPUT_TEXT), "Cannot find search field", 10);
    }

    public void waitForPageListItemTitleNotPresent() {
        this.waitForElementNotPresent(By.xpath(PAGE_LIST_ITEM_TITLE), "Articles is still present on the page", 5);
    }

    public void waitForSearchResultAndClick(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find search input " + substring, 5);
    }
}



