package stepDefinications;

import cucumberOptions.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;

public class LoginPageSteps {
    WebDriver driver;
    LoginPageObject loginPage;
    public LoginPageSteps()
    {
        this.driver= Hooks.openAndQuitBrowser();
        loginPage= PageGeneratorManager.getLoginpage(driver);
    }
    @Given("Open register page")
    public void openRegisterPage() {

    }

    @And("Submit valid infor to login form")
    public void submitValidInforToLoginForm() {
    }

    @When("Input to Email  {string}")
    public void inputToEmail(String arg0) {

    }
}
