package implementation;


import commonLibs.ICommonElements;
import org.apache.commons.io.FileUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import utilities.DriverFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

public class CommonElements implements ICommonElements {

    public Logger log = LogManager.getLogger(CommonElements.class);



    // The below abstracts methods defined in Interface ICommonElements are implemented in this class
    // Note we have not implemented all of them and can be done as per project requirement

    @Override
    public String getText(WebElement element) throws Exception {
        String elementText = "";
        try {
            log.debug("Driver finding the text from element");
            element.getText();
        } catch (NullPointerException ne) {
            System.out.println("The text of the element is null:");
            log.info("The text of the element is Null");
             log .error(ne.getMessage(), ne);
        }
        return elementText;
    }

    @Override
    public void setText(WebElement element, String input) throws Exception {
        //check if the passed value is not null
        if (input !=null){

            try {
                log.debug("Driver tryin to set the text");
                clearTextBox(element);
            } catch (Exception e) {
               log.info("Unable to Clear the text box");
               log.error(e.getMessage(),e);
            }
            try {
                element.sendKeys(input);
            } catch (Exception e) {
                log.info("Unable to set the value in text box");
                log.error(e.getMessage(),e);
            }
        }
    }

    @Override
    public void clearTextBox(WebElement element) throws Exception {

            log.debug("Driver trying to clear the text");
            String textInsideInputBox =element.getAttribute("value");
            if (!textInsideInputBox.isEmpty()){
                element.clear();
            } else {
                System.out.println("The input is empty");
            }

        }


    @Override
    public void click(WebElement element) throws Exception {
        try {
            log.debug("Driver trying to click the element");
            element.click();
        } catch (Exception e) {
            log.info("Unable to click the element");
            log.error(e.getMessage(),e);
        }
    }

    @Override
    public boolean isElementEnabled(WebElement element) throws Exception {
        try {
            log.debug("Driver trying to identify the element is enabled");
           return element.isEnabled();
        } catch (Exception e) {
            log.info("Element is not enabled");
            log.error(e.getMessage(),e);
            return false;
        }
    }

    @Override
    public boolean isElementDisplayed(WebElement element) throws Exception {
        try {
            log.debug("Driver trying to identify the element is displayed");
          return  element.isDisplayed();
        } catch (Exception e) {
            log.info("Element is not dislayed");
            log.error(e.getMessage(),e);
            return false;
        }
    }

    @Override
    public WebElement getElementByXpath(String xpath) throws Exception {
        WebElement element = DriverFactory.getInstance().getDriver().findElement(By.xpath(xpath));
        return element;
    }

    @Override
    public List<WebElement> getElementsByXpath(String xpath) throws Exception {
      List<WebElement> elements = DriverFactory.getInstance().getDriver().findElements(By.xpath(xpath));
      return elements;
    }

    @Override
    public WebElement waitForElement(WebElement element) throws Exception {
        try {
            return waitForElementToBeClickable(element);
        } catch (WebDriverException e) {
            String mesg ="Elements not found or not visible :";
            throw new WebDriverException(mesg+ e.getMessage());
        }
    }
    @Override
    public WebElement waitForElementToBeClickable(WebElement element) throws WebDriverException {
        Wait wait =  new FluentWait(DriverFactory.getInstance().getDriver())
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        WebElement elementFound = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(element));
        return elementFound;
    }

    @Override
    public WebElement invisibilityOfElementLocated(WebElement element){
        Wait wait =  new FluentWait(DriverFactory.getInstance().getDriver())
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        WebElement elementFound = (WebElement) wait.until(ExpectedConditions.invisibilityOfElementLocated((By) element));
        return elementFound;

    }

    @Override
    public boolean isElementClickable(WebElement element) throws Exception {
        try {
            if(element.getAttribute("class").contains("Inactive")){
                System.out.println("The element's class value contains Inactive phase. Actual value :"+element.getAttribute("class"));
                return false;
            } else {
                System.out.println("The element's class value does not contains Inactive phase. Actual value :"+element.getAttribute("class"));
                return true ;
            }
        } catch (WebDriverException e) {
            System.out.println("Webdriver exception occured (Description "+e+")");
            return false;
        }
    }

    @Override
    public void pageRefresh() {
        try {
            DriverFactory.getInstance().getDriver().navigate().refresh();
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(("Webdriver exception  occured :"+e+") "));
        }
    }

    @Override
    public void takeScreenShotwithEx(String stepname, Exception ex) throws Exception {

        File srcFile = ( (TakesScreenshot)(DriverFactory.getInstance().getDriver())).getScreenshotAs(OutputType.FILE);
        String screenShotName = "Failed_"+srcFile.getName();
        System.out.println("Failed Screenshot:" +screenShotName);
        log.error("Current step :" +stepname+" is failed");
        log.info(" Failed screenshot is captured as :"+screenShotName);

        try {
            log.debug("Starting to capture screenshot");

            FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir").toString() +"\\output\\Screenshots" +screenShotName));
            String completefiePath = System.getProperty("user.dir").toString() +"\\output\\Screenshots" +screenShotName ;
            log.info("Failed screenshot File path :" +completefiePath);
            log.error(ex.getMessage(),ex);
        } catch (IOException e) {
            log.info("Exception occured in screenshot capture");
            log.error(e.getMessage(), e);
        }
    }
}
