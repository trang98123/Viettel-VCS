package stepDefinications;

import com.smartosc.automation.cucumberOptions.Hooks;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

public class HomePageSteps {
    WebDriver driver;
    //tao contractor
  public HomePageSteps()
  {
      this.driver= Hooks.openAndQuitBrowser();
  }
    @Then("Home page displayed")
    public void homePageDisplayed() {
    }
}
