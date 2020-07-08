package ModernTestsV1;

import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.fluent.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class All3Tasks extends BaseTestUFG {

    @Parameters({"appUrl"})
    @Test
    public void task1CrossDeviceElementsTest(String appUrl) {
        // Update the Eyes configuration with test specific values
        Configuration testConfig = eyes.getConfiguration();
        testConfig.setTestName("Task 1");
        eyes.setConfiguration(testConfig);
        // Open Eyes, the application, test name and viewport size are already configured
        WebDriver driver = eyes.open(webDriver);
        driver.get(appUrl);

        // Visual checkpoint #1.
        eyes.checkWindow("Cross-Device Elements Test");
    }

    @Parameters({"appUrl"})
    @Test
    public void task2ShoppingExperienceTest(String appUrl) {
        // Update the Eyes configuration with test specific values
        Configuration testConfig = eyes.getConfiguration();
        testConfig.setTestName("Task 2");
        eyes.setConfiguration(testConfig);
        // Open Eyes, the application,test name and viewport size are already configured
        WebDriver driver = eyes.open(webDriver);
        // Now run the test
        driver.get(appUrl);   // navigate to website
        // Visual checkpoint #1.
        eyes.checkWindow("Filter Results");

        webDriver.findElement(By.xpath("//div[@id='filter_1']//label[contains(text(), 'Black')]")).click();
        webDriver.findElement(By.id("filterBtn")).click();

        // Visual checkpoint #2.
        eyes.check("Product Grid", Target.region(By.id("product_grid")));
    }

    @Parameters({"appUrl"})
    @Test
    public void task3ProductDetailsTest(String appUrl) {
        // Update the Eyes configuration with test specific values
        Configuration testConfig = eyes.getConfiguration();
        testConfig.setTestName("Task 3");
        eyes.setConfiguration(testConfig);
        // Open Eyes, the application,test name and viewport size are already configured
        WebDriver driver = eyes.open(webDriver);
        // Now run the test
        driver.get(appUrl);   // navigate to website

        webDriver.findElement(By.xpath("//div[@id='filter_1']//label[contains(text(), 'Black')]")).click();
        webDriver.findElement(By.id("filterBtn")).click();
        webDriver.findElement(By.xpath("//h3[contains(text(),'Appli Air x Night')]")).click();

        // Visual checkpoint #1.
        eyes.checkWindow("Product Details test");
    }
}
