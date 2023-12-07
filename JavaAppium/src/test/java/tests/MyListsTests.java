package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUiFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;


@Epic("Test for my lists")
public class MyListsTests extends CoreTestCase
{
    private static final String
            name_of_folder = "Learning programming",
            login = "Raffa1989",
            password = "Raffa666";

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article"), @Feature(value = "Navigation UI"), @Feature(value = "Authorization"), @Feature(value = "My list")})
    @DisplayName("Saving first article to my list")
    @Description("We saving first article to my list")
    @Step("Starting test testSaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveFirstArticleToMyList() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        } if (Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals(
                    "We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );

            ArticlePageObject.addArticlesToMySaved();
        }

        NavigationUI NavigationUI = NavigationUiFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyList();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()){
            myListsPageObject.openFolderByName(name_of_folder);
        }

        myListsPageObject.clickOnTheStarJava();

        myListsPageObject.swipeByArticleToDelete(article_title);
    }

    // Домашнее задание!
    // Ex17: Рефакторинг
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article"), @Feature(value = "Navigation UI"), @Feature(value = "Authorization"), @Feature(value = "My list")})
    @DisplayName("Saving two articles to my list")
    @Description("We saving two article to my list and then remove one of them")
    @Step("Starting test testSaveTwoArticlesToMyList")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveTwoArticlesToMyList() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        } if (Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals(
                    "We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );

            ArticlePageObject.addArticlesToMySaved();

            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("JavaScript");
            SearchPageObject.clickByArticleWithSubstring("High-level programming language");

            ArticlePageObject.addArticlesToMySaved();
        }

        NavigationUI NavigationUI = NavigationUiFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyList();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()){
            myListsPageObject.openFolderByName(name_of_folder);
        }

        myListsPageObject.clickOnTheStarJava();

        myListsPageObject.swipeByArticleToDelete(article_title);

        // для проверки вместо title я использовала ссылку на статью
        myListsPageObject.assertStayedArticleAboutJavaScriptForWebMobile();
    }
}
