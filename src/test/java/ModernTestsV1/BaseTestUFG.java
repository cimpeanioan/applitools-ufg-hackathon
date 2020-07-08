package ModernTestsV1;


import com.applitools.eyes.*;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTestUFG {
    private final int viewPortWidth = 1200;
    private final int viewPortHeight = 700;
    String myEyesServer = "https://eyes.applitools.com/"; //set to your server/cloud URL
    String appName = "AppliFashion";
    String batchName = "UFG Hackathon";
    private String apiKey = "qmXZNPwU7KUe1PlYvjVrGAWTHPOiHxGYyq5F1hIk1D4110";//System.getProperty("MY_APPLITOOLS_API_KEY"); //System.getenv("MY_APPLITOOLS_API_KEY");
    private EyesRunner runner = null;
    public com.applitools.eyes.config.Configuration suiteConfig;
    protected Eyes eyes;
    protected WebDriver webDriver;
//    public String appUrl = "https://demo.applitools.com/gridHackathonV1.html";  // V1
//    public String appUrl = "https://demo.applitools.com/gridHackathonV1.html";  // V2

    @BeforeSuite
    public void beforeTestSuite() {
        runner = new VisualGridRunner(10);
        // Create a configuration object, we will use this when setting up each test
        suiteConfig = new Configuration()
                // Visual Grid configurations
                .addBrowser(1200, 700, BrowserType.CHROME)  // Laptop
                .addBrowser(1200, 700, BrowserType.FIREFOX) // Laptop
                .addBrowser(1200, 700, BrowserType.EDGE_CHROMIUM)   // Laptop
                .addBrowser(768, 700, BrowserType.CHROME)   // Tablet
                .addBrowser(768, 700, BrowserType.FIREFOX)  // Tablet
                .addBrowser(768, 700, BrowserType.EDGE_CHROMIUM)    // Tablet
                .addDeviceEmulation(DeviceName.iPhone_X, ScreenOrientation.PORTRAIT)    // Mobile
                // Checkpoint configurations
                // Test specific configurations ....
                .setViewportSize(new RectangleSize(viewPortWidth, viewPortHeight))
                // Test suite configurations
                .setApiKey(apiKey)
                .setServerUrl(myEyesServer)
                .setAppName(appName)
                .setBatch(new BatchInfo(batchName)
                        /* ...other configurations */);
    }

    @BeforeMethod
    public void beforeEachTest(ITestResult result) {
        // Create the Eyes instance for the test and associate it with the runner
        eyes = new Eyes(runner);
        eyes.setConfiguration(suiteConfig);
        webDriver = new ChromeDriver();
    }

    @AfterMethod
    public void afterEachTest(ITestResult result) {
        // check if an exception was thrown
        boolean testFailed = result.getStatus() == ITestResult.FAILURE;
        if (!testFailed) {
            // Close the Eyes instance, no need to wait for results, we'll get those at the end in afterTestSuite
            eyes.closeAsync();
        } else {
            // There was an exception so the test may be incomplete - abort the test
            eyes.abortAsync();
        }
        webDriver.quit();
    }

    @AfterSuite
    public void afterTestSuite(ITestContext testContext) {
        //Wait until the test results are available and retrieve them
        TestResultsSummary allTestResults = runner.getAllTestResults(false);
        for (TestResultContainer result : allTestResults) {
            handleTestResults(result);
        }
    }

    void handleTestResults(TestResultContainer summary) {
        Throwable ex = summary.getException();
        if (ex != null) {
            System.out.printf("System error occured while checking target.\n");
        }
        TestResults result = summary.getTestResults();
        if (result == null) {
            System.out.printf("No test results information available\n");
        } else {
            System.out.printf("URL = %s, AppName = %s, testname = %s, Browser = %s,OS = %s, viewport = %dx%d, matched = %d,mismatched = %d, missing = %d,aborted = %s\n",
                    result.getUrl(),
                    result.getAppName(),
                    result.getName(),
                    result.getHostApp(),
                    result.getHostOS(),
                    result.getHostDisplaySize().getWidth(),
                    result.getHostDisplaySize().getHeight(),
                    result.getMatches(),
                    result.getMismatches(),
                    result.getMissing(),
                    (result.isAborted() ? "aborted" : "no"));
        }
    }
}