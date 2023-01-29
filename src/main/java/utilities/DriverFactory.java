package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class DriverFactory {
    Logger log = (Logger) LoggerFactory.getLogger(DriverFactory.class);
    PropLocation file = new PropLocation();
    ReadProperties loc = new ReadProperties(file.location());
    private WebDriver driver;
    private static DriverFactory myobj = null;

    //This is our DriverFactory Class where the control of Test starts with
    //We have defined the constructor as Private so that the class cannot be instantiated in other class.
    //At the same time  we are instantiating its object by the getDriver method
    // We have implemented the OOPS based designed pattern Singleton Design Pattern

    private DriverFactory() {

    }

    public WebDriver getDriver() {

        return this.driver;
    }

    private void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public static DriverFactory getInstance() {
        if (myobj == null) {
            myobj = new DriverFactory();
            return myobj;
        } else {
            return myobj;
        }
    }

    public void initializeDriver() {
        try {
            log.info("*****************Printing the logs*******************");
            if (loc.readProperty("Browser").equalsIgnoreCase("chrome")) {
                log.info("driver is using : webdriver.chrome.driver");
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                driver = new ChromeDriver(options);
                log.info("Driver Initialization completed");
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                log.info("driver timeout: 10, TimeUnit.SECONDS ");

            } else if (loc.readProperty("Browser").equalsIgnoreCase("Firefox")) {
                log.info("driver is using : webdriver.gecko.driver");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                log.info("driver timeout: 10, TimeUnit.SECONDS");
            } else if (loc.readProperty("Browser").equalsIgnoreCase("Headless")) {
                log.info("driver is using : webdriver.chrome.driver");
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("window-size=1200x600");
                driver = new ChromeDriver(options);
            }
        } catch (Exception ex) {
            log.error("Exception occured :" + ex.getMessage(), ex);
            ex.printStackTrace();
        }

    }


}

