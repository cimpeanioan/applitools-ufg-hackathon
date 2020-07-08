package TraditionalTestsV2;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utils.DriverFactory;
import utils.Utility;

import java.io.BufferedWriter;
import java.io.FileWriter;


public class BaseTest {

    protected RemoteWebDriver driver;
    public static int width;
    public static int height;
    public static String reportFileName = "Traditional-V2-TestResults.txt";
    public static String viewport;
    public static String device;
    public static String browser;
    String url = "https://demo.applitools.com/gridHackathonV2.html";    // V1
    Utility utils;

    @Parameters({"browserType", "width", "height", "device"})
    @BeforeMethod
    public void setup(String browserType, int width, int height, String device) {
        BaseTest.browser = browserType;
        BaseTest.width = width;
        BaseTest.height = height;
        BaseTest.device = device;
        viewport = width + " x " + height;
        driver = new DriverFactory(browser).webDriver;
        System.out.println(" Running on " + driver.getCapabilities().getBrowserName() + " " + driver.getCapabilities().getVersion());
        driver.get(url);
        utils = new Utility(driver);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null)
            driver.quit();
        driver = null;
    }

    /**
     * A Helper to print the test result in the following format:
     * Task: <Task Number>, Test Name: <Test Name>, DOM Id:: <id>, Browser: <Browser>, Viewport: <Width x Height>, Device<Device type>, Status: <Pass | Fail>
     * <p>
     * Example: Task: 1, Test Name: Search field is displayed, DOM Id: DIV__customsear__41, Browser: Chrome, Viewport: 1200 x 700, Device: Laptop, Status: Pass
     *
     * @param task             int - 1, 2 or 3
     * @param testName         string - Something meaningful. E.g. 1.1 Search field is displayed
     * @param domId            string - DOM ID of the element
     * @param comparisonResult boolean - The result of comparing the "Expected" value and the "Actual" value.
     * @return boolean - returns the same comparison result back so that it can be used for further Assertions in the test code.
     */

    public boolean hackathonReporter(int task, String testName, String domId, boolean comparisonResult) {
        try (var writer = new BufferedWriter(new FileWriter(reportFileName, true))) {
            writer.write("Task: " + task + ", Test Name: " + testName + ", DOM Id: " + domId + ", Browser: " + browser
                    + ", Viewport: " + viewport + ", Device: " + device + ", Status: " + (comparisonResult ? "Pass" : "Fail"));
            writer.newLine();
        } catch (Exception e) {
            System.out.println("Error writing to report file");
            e.printStackTrace();
        }
        //returns the result so that it can be used for further Assertions in the test code.
        return comparisonResult;
    }
}

