package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    private static HomePageObject homePageObject;
    private static LoginPageObject loginPageObject;
    private static MyDashboardPageObject myDashboardPageObject;
    private static MobilePgeObject mobilePageObject;
    private static RegisterPageObject registerPageObject;

    private PageGeneratorManager() {

    }

    public static HomePageObject getHomePage(WebDriver driver) {
        if (homePageObject == null) {
            homePageObject = new HomePageObject(driver);
        }
        return homePageObject;
    }

    public static RegisterPageObject getRegisterPage(WebDriver driver)
    {
        if(registerPageObject==null)
        {
            registerPageObject= new RegisterPageObject(driver);
        }
        return  registerPageObject;
    }

    public static LoginPageObject getLoginpage(WebDriver driver) {
        if (loginPageObject == null) {
            loginPageObject = new LoginPageObject(driver);
        }
        return loginPageObject;
    }

    public static MyDashboardPageObject getmyDashboard(WebDriver driver) {
        if (myDashboardPageObject == null) {
            myDashboardPageObject = new MyDashboardPageObject(driver);
        }
        return myDashboardPageObject;
    }

    public static MobilePgeObject getMobilePgeObject(WebDriver driver) {
        if (mobilePageObject == null) {
            mobilePageObject = new MobilePgeObject(driver);
        }
        return mobilePageObject;
    }
}
