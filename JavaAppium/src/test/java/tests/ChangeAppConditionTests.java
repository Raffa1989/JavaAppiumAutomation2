package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;


@Epic("Test for change app condition")
public class ChangeAppConditionTests extends CoreTestCase
{
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Change screen orientation on search results (only Android an iOS)")
    @Description("We change orientation the screen and compare search result")
    @Step("Starting test testChangeScreenOrientationOnSearchResults")
    @Severity(value = SeverityLevel.NORMAL)
    public void testChangeScreenOrientationOnSearchResults() {

        if (Platform.getInstance().isMW()) {
            return;
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("язык программирования");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    /* Тест падает. Нужно переустанавливать аппиум и прочее. Пока решила не заморачиваться
    @Test
    public void testCheckSearchArticleInBackground() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Пропустить')]"),
                "Cannot find 'Пропустить'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Поиск по Википедии']"),
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//android.widget.EditText[@text='Поиск по Википедии']"),
                "Java",
                "Cannot find search input 2",
                10
        );

        // название статьи состоит из двух строк. одна "Java", вторая "язык программирования"
        // взяла для теста вторую строку
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='язык программирования']"),
                "Cannot find search input",
                5
        );

        // driver.runAppInBackground(3);
        //driver.resetApp();

        // Сворачиваем приложение
        driver.runAppInBackground(5);


// Восстанавливаем приложение
        // driver.launchApp();

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='язык программирования']"),
                "Cannot find article after returning from background",
                5
        );
    }
     */
}
