package pageObjects;

import commons.BasePage;
import pageUIs.MyDashboardPageUI;
import org.openqa.selenium.WebDriver;


public class MyDashboardPageObject extends BasePage {
    WebDriver driver;

    public MyDashboardPageObject(WebDriver driver) {

        this.driver = driver;
    }

    public boolean isMyDashboarHeaderDisplayed() {
        // TODO Auto-generated method stub
        waitForElementVisible(driver, MyDashboardPageUI.MY_DASHBOARD_HEADER_TEXT);
        return isElmentDisplayed(driver, MyDashboardPageUI.MY_DASHBOARD_HEADER_TEXT);
    }




}
