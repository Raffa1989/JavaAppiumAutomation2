package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

import org.openqa.selenium.WebElement;
import java.util.List;


@Epic("Test for search")
public class SearchTests extends CoreTestCase {
    @Test
    @Feature(value = "Search")
    @DisplayName("Testing search")
    @Description("We testing search")
    @Step("Starting test testSearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSearch() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");

        Thread.sleep(3000);
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Testing cancel search")
    @Description("We testing cancel search")
    @Step("Starting test testCancelSearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCancelSearch() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java"); // я оставила этот шаг, так как в моей версии приложения кнопка "Назад" исчезает
        SearchPageObject.waitForCancelButtonAppear();
        //SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonDisappear();

        Thread.sleep(5000);
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Amount of not empty search")
    @Description("We testing that search not empty")
    @Step("Starting test testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfNotEmptySearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        //SearchPageObject.waitLanguageButtonAndClick();
        String search_line = "Linkin Park Diskography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found a few results!",
                amount_of_search_results > 0
        );
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Amount of empty search")
    @Description("We testing that search empty")
    @Step("Starting test testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfEmptySearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "fwplllk";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    //Ex2: Создание метода (вторая часть задания)
    @Test
    @Feature(value = "Search")
    @DisplayName("The field contains text")
    @Description("We testing that field contains text")
    @Step("Starting test testFieldContainsText")
    @Severity(value = SeverityLevel.NORMAL)
    public void testFieldContainsText() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.SearchInputPlaceholderText();
    }


    // Домашнее задание!!!!!!!!!! Ex8
    //Ex3: Тест: отмена поиска
    @Test
    @Feature(value = "Search")
    @DisplayName("The search disappeared")
    @Description("We testing that search disappeared")
    @Step("Starting test testSearchDisappeared")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSearchDisappeared() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitLanguageButtonAndClick();
        String search_line = "Moskow";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForPageListItemTitlePresent();

        List<WebElement> search_results = SearchPageObject.getSearchResults();
        Assert.assertTrue("Less than two articles found", search_results.size() >= 2);

        SearchPageObject.clickElementSearchInputText();
        SearchPageObject.clearElementSearchInputText();
        SearchPageObject.waitForPageListItemTitleNotPresent();
    }

/*
    //Ex4*: Тест: проверка слов в поиске
    @Test
    public void testCheckingWordsInSearch() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Пропустить')]"),
                "Cannot find 'Пропустить'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Поиск по Википедии']"),
                "Cannot find search input",
                10
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//android.widget.EditText[@text='Поиск по Википедии']"),
                "Java",
                "Cannot find 'Java'",
                10
        );

        List<WebElement> searchResults = driver.findElements(By.id("page_list_item_title"));
        for (WebElement result : searchResults) {
            String resultText = result.getText();
            Assert.assertTrue("Слово 'Java' не найдено в результатах поиска", resultText.contains("Java"));
        }
    }
    */
}

