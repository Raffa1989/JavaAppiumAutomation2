package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import src.tests.ArticleTests;
import src.tests.ChangeAppConditionTests;
import src.tests.MyListsTests;
import src.tests.SearchTests;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ArticleTests.class,
        ChangeAppConditionTests.class,
        MyListsTests.class,
        SearchTests.class
})

public class TestSuite {
}
