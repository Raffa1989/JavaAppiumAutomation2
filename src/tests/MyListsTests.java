package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUiFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;


// оба теста падают
public class MyListsTests extends CoreTestCase
{
    private static final String name_of_folder = "язык программирования";


    @Test
    public void testSaveFirstArticleToMyList() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("язык программирования");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();

        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUiFactory.get(driver);
        NavigationUI.clickMyList();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            myListsPageObject.openFolderByName(name_of_folder);
        }

        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);
        MyListPageObject.openFolderByName(name_of_folder);

        NavigationUI.clickItemTitle();

        // нет свайпа. ОШИБКА!!!
        MyListPageObject.swipeByArticleToDelete(article_title);
    }

    // Домашнее задание!!!!!!!!!! Ex8
    // Ex5: Тест: сохранение двух статей
    @Test
    public void testSaveTwoArticlesToMyList() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("язык программирования");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        MyListsPageObject.addArticleAboutJavaScript(name_of_folder);

        NavigationUI NavigationUI = NavigationUiFactory.get(driver);
        NavigationUI.lookAllList();

        // тоже нет свайпа. ОШИБКА!!!!

        MyListsPageObject.swipeByArticleToDelete("Java");
        MyListsPageObject.assertStayedArticleAboutJavaScript();
    }
}
