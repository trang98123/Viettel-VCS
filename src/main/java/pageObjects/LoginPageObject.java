package pageObjects;

import commons.BasePage;
import pageUIs.LoginPageUI;
import org.openqa.selenium.WebDriver;



public class LoginPageObject extends BasePage {
    WebDriver driver;

    public LoginPageObject(WebDriver driver) {

        this.driver = driver;
    }

    public void enterToEmailtextbox(String emailAddress) {
        // TODO Auto-generated method stub
        waitForAllElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
        sendkeyToElementByJS(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);
    }

    public void enterToPasswordTextbox(String password) {
        // TODO Auto-generated method stub
        waitForAllElementVisible(driver, LoginPageUI.PASSWORD_ADDRESS_TEXTBOX);
        senkeyToElement(driver, LoginPageUI.PASSWORD_ADDRESS_TEXTBOX, password);
    }

    public void clickToLoginButton() {
        // TODO Auto-generated method stub
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }

    public String getEmptyEmailErrorMessage() {
        waitForAllElementVisible(driver, LoginPageUI.EMPTY_EMAIL_ERROR_MASSAGE);
        // TODO Auto-generated method stub
        return getElementText(driver, LoginPageUI.EMPTY_EMAIL_ERROR_MASSAGE);
    }

    public String getEmptyPasswordErrorMessage() {
        // TODO Auto-generated method stub
        waitForAllElementVisible(driver, LoginPageUI.EMPTY_PASSWORD_ERROR_MASSAGE);

        return getElementText(driver, LoginPageUI.EMPTY_PASSWORD_ERROR_MASSAGE);
    }

    public String getInvalidEmailErrorMessage() {
        // TODO Auto-generated method stub
        waitForAllElementVisible(driver, LoginPageUI.INVALID_EMAIL_ERROR_MASSAGE);
        return getElementText(driver, LoginPageUI.INVALID_EMAIL_ERROR_MASSAGE);
    }

    public String getInvalidPasswordErrorMessage() {
        // TODO Auto-generated method stub
        waitForAllElementVisible(driver, LoginPageUI.INVALID_PASSWORD_ERROR_MASSAGE);
        return getElementText(driver, LoginPageUI.INVALID_PASSWORD_ERROR_MASSAGE);
    }

    public String getInvalidEmailOrPasswordErrorMessage() {
        // TODO Auto-generated method stub
        waitForAllElementVisible(driver, LoginPageUI.INVALID_EMAIL_OR_PASSWORD_ERROR_MASSAGE);
        return getElementText(driver, LoginPageUI.INVALID_EMAIL_OR_PASSWORD_ERROR_MASSAGE);
    }

    public MyDashboardPageObject loginToSystem(String emailAddress, String password) {
        // TODO Auto-generated method stub
        enterToEmailtextbox(emailAddress);
        enterToPasswordTextbox( password);
        clickToLoginButton();
        return PageGeneratorManager.getmyDashboard(driver);
    }





}

