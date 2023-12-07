package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    private static final String
        LOGIN_BUTTON = "css:body > div.drawer-container.view-border-box > div.drawer.drawer-container__drawer.position-fixed.visible > a",
        LOGIN_INPUT = "css:input[name='wpName']",
        PASSWORD_INPUT = "css:input[name='wpPassword']",
        SUBMIT_BUTTON = "css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Click auth button")
    public void clickAuthButton() {
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 5);
    }

    @Step("Entering login data")
    public void enterLoginData(String login, String password) {
        this.waitForElementAndClear(LOGIN_INPUT, login,  5);
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find and put a login to the login input", 5);
        this.waitForElementAndClear(PASSWORD_INPUT, password,  5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and put a password to the password input", 5);
    }

    @Step("Click by submit button")
    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit auth button", 5);
    }
}
