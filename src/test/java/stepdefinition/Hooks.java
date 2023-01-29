package stepdefinition;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.DriverFactory;

public class Hooks {
    Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void initializeDriver() {
        DriverFactory.getInstance().initializeDriver();
    }

    @After
    public void quitDriver() {
        DriverFactory.getInstance().getDriver().quit();
    }
}
