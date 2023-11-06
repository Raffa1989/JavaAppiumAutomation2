package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;


public class MyListsTests extends CoreTestCase
{
    @Test
    public void testSaveFirstArticleToMyList() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("язык программирования");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();

        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);

        NavigationUI.clickItemTitle();

        MyListPageObject.swipeByArticleToDelete(article_title);
    }

    // Домашнее задание!!!!!!!!!! Ex8
    // Ex5: Тест: сохранение двух статей
    @Test
    public void testSaveTwoArticlesToMyList() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("язык программирования");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.addArticleAboutJavaScript(name_of_folder);

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.lookAllList();

        MyListsPageObject.swipeByArticleToDelete("Java");
        MyListsPageObject.assertStayedArticleAboutJavaScript();
    }
}
