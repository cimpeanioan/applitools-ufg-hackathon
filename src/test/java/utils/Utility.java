package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {

    public WebDriver driver;
    public WebDriverWait wait;

    public Utility(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 25);
    }

    public <V> V optionalWaitUntil(int seconds, com.google.common.base.Function<? super WebDriver, V> condition) {
        wait = new WebDriverWait(driver, seconds);
        try {
            WebDriverWait optionalWait = new WebDriverWait(driver, seconds);
            return optionalWait.until(condition);
        } catch (Exception e) {
            return null;
        }
    }

//    public void scrollToElement(By by) {
//        try {
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
//            Thread.sleep(1000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
