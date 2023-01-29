package runner;
import Cucumber_Listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import stepdefinition.Hooks;

import java.io.File;

@CucumberOptions(
        features ={"src/test/resources/features/"},
        glue ={"stepdefinition"},
        monochrome = true,
        tags = {"@test"},
        plugin = {"Cucumber_Listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"}
)
@RunWith(Cucumber.class)
public class Runner extends Hooks {

    @AfterClass
    public static void writeExtentReport(){
        Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setSystemInfo("os", "WINDOWS OSX");
        Reporter.setTestRunnerOutput("Sample test runner output message");
    }

}
