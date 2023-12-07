package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;


@Epic("Test for articles")
public class ArticleTests extends CoreTestCase
{
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Compare article title with expected one")
    @Description("We open 'Java Object-oriented programming language' and make sure the title is expends")
    @Step("Starting test testCompareArticleTitle")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCompareArticleTitle() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        //ArticlePageObject.takeScreenshot("article_page");

        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Swipe article to the footer")
    @Description("We open article and swipe it to the footer")
    @Step("Starting test testSwipeArticle")
    @Severity(value = SeverityLevel.MINOR)
    public void testSwipeArticle() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        //SearchPageObject.waitLanguageButtonAndClick();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }

    // Домашнее задание!!!!!!!!!! Ex8
    // Ex6: Тест: assert title
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Assert title present in the article")
    @Description("We open 'Java Object-oriented programming language' and checking title present in the article")
    @Step("Starting test testAssertTitlePresentInArticle")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAssertTitlePresentInArticle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResultAndClick("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.assertArticleTitleWithoutWait();
    }
}
