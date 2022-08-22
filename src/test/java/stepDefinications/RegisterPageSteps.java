package stepDefinications;

import commons.BasePage;
import cucumberOptions.Hooks;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;

public class RegisterPageSteps extends BasePage {
    WebDriver driver;
    RegisterPageObject registerPage;
    HomePageUI homePageUI;

    public RegisterPageSteps() {
        this.driver = Hooks.openAndQuitBrowser();
        registerPage= PageGeneratorManager.getRegisterPage(driver);
    }

    @When("Input to Email textbox")
    public void inputToEmailTextbox(String value) {
        waitForElementVisible(driver, homePageUI.NAME);
        senkeyToElement(driver,homePageUI.NAME,value);
    }

    @And("Click to Submit")
    public void clickToSubmit() {

    }

    @Then("Get User and password info")
    public void getUserAndPasswordInfo() {

    }


    @When("Back to Login page")
    public void backToLoginPage() {
    }
}
