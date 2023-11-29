package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import org.openqa.selenium.WebElement;
import java.util.List;


public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("язык программирования");

        Thread.sleep(3000);
    }

    @Test
    public void testCancelSearch() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java"); // я оставила этот шаг, так как в моей версии приложения кнопка "Назад" исчезает
        SearchPageObject.waitForCancelButtonAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonDisappear();

        Thread.sleep(5000);
    }

    @Test
    public void testAmountOfNotEmptySearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitLanguageButtonAndClick();
        String search_line = "Linkin Park Diskography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found a few results!",
                amount_of_search_results > 0
        );
    }

    @Test
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
    public void testFieldContainsText() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.testSearchInputPlaceholderText();
    }


    // Домашнее задание!!!!!!!!!! Ex8
    //Ex3: Тест: отмена поиска
    @Test
    public void testSearchDisappeared() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitLanguageButtonAndClick();
        String search_line = "Moskow";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForPageListItemTitlePresent();

        List<WebElement> search_results = SearchPageObject.getSearchResults();
        assertTrue("Less than two articles found", search_results.size() >= 2);

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
            assertTrue("Слово 'Java' не найдено в результатах поиска", resultText.contains("Java"));
        }
    }
    */
}

