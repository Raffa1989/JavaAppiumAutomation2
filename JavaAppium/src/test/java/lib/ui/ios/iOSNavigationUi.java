package lib.ui.ios;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSNavigationUi extends NavigationUI {

    static {
        MY_LIST_LINK = "id:Saved";
    }

    public iOSNavigationUi(RemoteWebDriver driver)
    {
        super(driver);
    }
}
