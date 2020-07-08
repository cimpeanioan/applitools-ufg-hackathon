package utils;

import TraditionalTestsV1.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public RemoteWebDriver webDriver;
    public DriverFactory(String browser) {
        setNewDriver(browser);
    }

    public void setNewDriver(String browser) {
        try {
            if (webDriver != null) {
                webDriver.close();
                webDriver.quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            webDriver = null;
        }
        try {
            String browserType = browser;
            if (browserType == null) {
                return;
            }

            switch (browserType.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments(String.format("window-size=%s,%s", BaseTest.width, BaseTest.height));
                    WebDriverManager.chromedriver().setup();
                    webDriver = new ChromeDriver(chromeOptions);
                    break;

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments(String.format("window-size=%s,%s", BaseTest.width, BaseTest.height));
                    WebDriverManager.firefoxdriver().setup();
                    webDriver = new FirefoxDriver(firefoxOptions);
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.setCapability("window-size",BaseTest.width+ "*"+BaseTest.height);
                    webDriver = new EdgeDriver();
                    break;

                default:
                    System.out.println("Please specify the browser or add new one if needed!");
                    break;
            }
            webDriver.manage().window().setSize(new Dimension(BaseTest.width, BaseTest.height));
            webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
