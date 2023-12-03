package lib.ui.factories;

import lib.Platform;
import lib.ui.NavigationUI;
import lib.ui.android.AndroidNavigationUi;
import lib.ui.ios.iOSNavigationUi;
import lib.ui.mobile_web.MWNavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUiFactory {

    public static NavigationUI get(RemoteWebDriver driver)
    {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUi(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new iOSNavigationUi(driver);
        } else {
            return new MWNavigationUI(driver);
        }
    }
}
