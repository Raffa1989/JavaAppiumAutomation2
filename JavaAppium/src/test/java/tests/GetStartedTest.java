package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

@Epic("Get started test (for iOS)")
public class GetStartedTest extends CoreTestCase {

    @Test
    @Feature(value = "Welcome Page")
    @DisplayName("Skipping welcome page (only iOS)")
    @Description("We skipping welcome page")
    @Step("Starting test testPassThroughWelcome")
    @Severity(value = SeverityLevel.NORMAL)
    public void testPassThroughWelcome()
    {
        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMW())) {
            return;
        }

        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.waitForLearnMoreLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWayToExploreText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForAddOrEditPreferredLangText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForLearnMoreAboutDataCollecedText();
        WelcomePage.clickGetStartedButton();
    }
}
