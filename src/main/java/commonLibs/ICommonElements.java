package commonLibs;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface ICommonElements {

    // Here we have defined few Abstract common methods which are supposed to be used throughout the project

    // The Implementing Class for these methods is defined in package "Implementation" and class CommonElements

    public String getText(WebElement element) throws Exception;

    public void setText(WebElement element, String input) throws Exception;

    public void clearTextBox(WebElement element) throws Exception;

    public void click(WebElement element) throws Exception;

    public boolean isElementEnabled(WebElement element) throws Exception;

    public boolean isElementDisplayed(WebElement element) throws Exception;

    public WebElement getElementByXpath(String xpath) throws Exception;

    public List<WebElement> getElementsByXpath(String xpath) throws Exception;

    public WebElement waitForElement(WebElement element) throws Exception;

    public WebElement waitForElementToBeClickable(WebElement element) throws WebDriverException;

    WebElement invisibilityOfElementLocated(WebElement element);

    public boolean isElementClickable(WebElement element) throws Exception;

    public void pageRefresh();

    public void takeScreenShotwithEx(String MethodName, Exception ex) throws Exception;

}
