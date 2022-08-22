package cucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(glue = "com.smartosc.automation.cucumber.stepDefinitions",
        features = "src/test/java/com/smartosc/automation/features",
        plugin = { "pretty", "html:target/site/cucumber-reports","json:target/site/cucumber.json" },
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = {"@register_login"})

@Test
public class RunCucumberTest extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
