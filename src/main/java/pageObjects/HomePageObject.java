package pageObjects;

import com.smartosc.automation.commons.BasePage;
import com.smartosc.automation.pageUI.HomePageUI;
import org.openqa.selenium.WebDriver;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {

        this.driver = driver;
    }

    public LoginPageObject clickToMyAccountFooterLink() {
        // TODO Auto-generated method stub
        waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_FOOTER);
        clickToElement(driver, HomePageUI.MY_ACCOUNT_FOOTER);
        return PageGeneratorManager.getLoginpage(driver);
    }

    public MobilePgeObject openMobilePage() {
        // TODO Auto-generated method stub
        waitForElementClickable(driver, HomePageUI.MOBILE_PAGE);
        clickToElement(driver,  HomePageUI.MOBILE_PAGE);
        return PageGeneratorManager.getMobilePgeObject(driver);
    }


}
