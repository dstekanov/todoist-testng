package com.todoist.webdriver.local;

import com.todoist.webdriver.Browser;
import com.todoist.webdriver.capabilities.DriverCapabilities;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface LocalBrowser {

    public static RemoteWebDriver driverFor(Browser browser) {
        Capabilities capabilities = DriverCapabilities.capabilitiesFor(browser).get();
        switch (browser) {
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
                return new FirefoxDriver(new FirefoxOptions(capabilities));
            case EDGE:
                System.setProperty("webdriver.edge.driver", "drivers\\MicrosoftWebDriver.exe");
                return new EdgeDriver(new EdgeOptions().merge(capabilities));
            case IE:
                System.setProperty("webdriver.ie.driver", "drivers\\IEDriverServer.exe");
                return new InternetExplorerDriver(new InternetExplorerOptions(capabilities));
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
                return new ChromeDriver(new ChromeOptions().merge(capabilities));
            default:
                throw new IllegalArgumentException(String.format("Incorrect Browser = [%s]", browser.getName()));
        }
    }
}
