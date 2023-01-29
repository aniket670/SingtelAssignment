package pages;

import implementation.CommonElements;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.DriverFactory;
import utilities.PropLocation;
import utilities.ReadProperties;

import java.util.List;

public class ToDoPage extends CommonElements {
    public WebDriver driver;
    Logger logger = LoggerFactory.getLogger(ToDoPage.class);
    PropLocation file = new PropLocation();
    ReadProperties elementloc = new ReadProperties(file.elementlocation());

    public ToDoPage() {
        this.driver = DriverFactory.getInstance().getDriver();
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(how = How.XPATH, using = "//input[@class='new-todo']")
    private WebElement newToDo_txt;

    @FindBy(how = How.XPATH, using = "//*[@class='todo-list']/li")
    private List<WebElement> ToDoList;

    @FindBy(how = How.XPATH, using = "//input[@class='toggle']")
    private List<WebElement> select_chckbox;

    private String chkBox = "//input[@class='toggle'][%s]";
    @FindBy(how = How.XPATH, using = "//ul[@class='todo-list']/li/div/child::button[1]")
    private WebElement delete_btn;
    @FindBy(how = How.XPATH, using = "//*[@class='todo-count']")
    private WebElement itemsLeft_txt;
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Active')]")
    private WebElement activeTab;
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'All')]")
    private WebElement allTab;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Completed')]")
    private WebElement completedTab;
    @FindBy(how = How.CLASS_NAME, using = "clear-completed")
    private WebElement ClearComp_btn;


    public void navigateToApplication() {
        driver.get(elementloc.readProperty("WebUrl"));
        driver.manage().window().maximize();
        log.info("User navigated to the application successfully");
    }

    public void createToDoRecord(String input) {
        try {
            if (newToDo_txt.isDisplayed()) {
                log.info("The todo input field is present");
                newToDo_txt.sendKeys(input);
                newToDo_txt.sendKeys(Keys.ENTER);

            }
        } catch (NoSuchElementException ne) {
            log.error("The error occured :" + ne.getMessage());
            ne.printStackTrace();
        }
    }

    public boolean verifyRecordCreated(String input, String tabName) {
        if (tabName.equalsIgnoreCase("All")) {
            waitForElementToBeClickable(allTab).click();
            if (!ToDoList.isEmpty()) {
                for (int i = 0; i < ToDoList.size(); i++) {
                    if (ToDoList.get(i).getText().equalsIgnoreCase(input)) {
                        return true;
                    }
                }
            }

        } else if (tabName.equalsIgnoreCase("Active")) {
            waitForElementToBeClickable(activeTab).click();
            if (!ToDoList.isEmpty()) {
                for (int i = 0; i < ToDoList.size(); i++) {
                    if (ToDoList.get(i).getText().equalsIgnoreCase(input)) {
                        return true;
                    }
                }
            }

        } else if (tabName.equalsIgnoreCase("Completed")) {
            waitForElementToBeClickable(completedTab).click();
            if (!ToDoList.isEmpty()) {
                for (int i = 0; i < ToDoList.size(); i++) {
                    if (ToDoList.get(i).getText().equalsIgnoreCase(input)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean verifyRecordInTabs(String input, String tabName) {
        try {
            if (tabName.equalsIgnoreCase("All")) {
                waitForElement(activeTab);
                if (activeTab.isEnabled())
                    activeTab.click();
                int listLength = ToDoList.size();
                for (int i = 0; i < listLength; i++) {
                    if (ToDoList.get(i).getText().equalsIgnoreCase(input))
                        return true;
                }
            } else if (tabName.equalsIgnoreCase("Active")) {
                waitForElement(activeTab);
                if (activeTab.isEnabled())
                    activeTab.click();
                int listLength = ToDoList.size();
                for (int i = 0; i < listLength; i++) {
                    if (ToDoList.get(i).getText().equalsIgnoreCase(input))
                        return true;
                }
            } else if (tabName.equalsIgnoreCase("Completed")) {
                waitForElementToBeClickable(completedTab);
                if (completedTab.isEnabled())
                    completedTab.click();
                int listLength = ToDoList.size();
                for (int i = 0; i < listLength; i++) {
                    if (ToDoList.get(i).getText().equalsIgnoreCase(input))
                        return true;
                }
            }
        } catch (Exception e) {
            log.error("The error message is :" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public void deleteToDoRecord(String input) {
        Actions actions = new Actions(DriverFactory.getInstance().getDriver());
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('display','visibility:visible;');",
                delete_btn);
        if (!ToDoList.isEmpty()) {
            for (int i = 0; i < ToDoList.size(); i++) {
                if (ToDoList.get(i).getText().equalsIgnoreCase(input)) {
                    jse.executeScript("arguments[0].click();", delete_btn);
                }
            }
        } else {
            log.error("The record to be deleted not found");
            Assert.fail();
        }
    }

    public void selectRecToBeDone(String toDoRecord) {
        try {
              waitForElement(allTab).click();
            if (!ToDoList.isEmpty()) {
                for (int i = 0; i < select_chckbox.size(); i++) {
                    if (ToDoList.get(i).getText().equalsIgnoreCase(toDoRecord)) {
                        WebElement selectChkbox = driver.findElement(By.xpath(String.format(chkBox, i + 1)));
                        selectChkbox.click();
                    }
                }
            }
        } catch (Exception e) {
            Assert.fail();
        }
    }

    public boolean isClearCompletedPresent() {
        try {
            return isElementDisplayed(ClearComp_btn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void clickClearCompleted() {
        try {
            waitForElement(ClearComp_btn).click();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public boolean verifyClearCompIsHidden() {
       return ClearComp_btn.isDisplayed();
    }
}
