package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.Utility;

/* Generic class used to initialize the objects that are used in the Page Objects
 * along with abstract method waitForPageToLoad that should be Overwrite in the child class*/

public abstract class AbstractPageObject {

    protected WebDriver driver;
    protected Utility utils;

    public AbstractPageObject(WebDriver driver) {
        this.driver = driver;
        utils = new Utility(driver);
        PageFactory.initElements(driver, this);
        waitForPageToLoad();
    }

    /* Method used to add explicit waits for elements that
     *should ALWAYS be visible when accessing a specific page object.
     *This method will be called after the constructor is initialized */
    public abstract void waitForPageToLoad();

}
