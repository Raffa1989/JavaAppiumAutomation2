package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;


public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception {

        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
    }

    @Override
    // Ex7*: Поворот экрана
    protected void tearDown() throws Exception
    {
        driver.rotate(ScreenOrientation.PORTRAIT); // Восстанавливаем экран в портретное положение
        driver.quit();

        super.tearDown();
    }

    protected void rotateScreenPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    private void skipWelcomePageForIOSApp()
    {
        if(Platform.getInstance().isIOS()) {
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }

    /* методо ранее не срабатывал. поэтому закоментила
    protected void backgroundApp(int seconds)
    {
        driver.runAppInBackground(seconds);
    }
     */
}
