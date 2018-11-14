package com.todoist.webdriver;

import com.google.inject.Inject;
import com.todoist.utils.Config;
import com.todoist.webdriver.capabilities.DriverCapabilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.SkipException;

import javax.inject.Named;
import java.net.URL;

public class RemoteDriverLauncher implements WebDriverLauncher {

    private final Logger logger = LogManager.getLogger(getClass());
    @Inject
    private WebDriverEventListener[] listeners;
    @Inject
    @Named("grid_port")
    private Integer port;

    @Override
    public WebDriver start() {
        final Browser browser = Config.getBrowser();
        logger.info(String.format("Starting Remote WebDriver. Browser==[%s]...", browser));

        Capabilities capabilities = DriverCapabilities.capabilitiesFor(browser).get();

        DesiredCapabilities selenoidCapabilities = new DesiredCapabilities(capabilities);
        selenoidCapabilities.setCapability("enableVNC", true);
        selenoidCapabilities.setCapability("enableVideo", true);

        try {
            URL hubURL = new URL("http", Config.getGridHost(), port, "/wd/hub");
            RemoteWebDriver remoteWD = new RemoteWebDriver(hubURL, selenoidCapabilities);
            remoteWD.manage().window().setSize(new Dimension(1920, 1080));

            EventFiringWebDriver wd = new EventFiringWebDriver(new Augmenter().augment(remoteWD));
            for (WebDriverEventListener listener : listeners) {
                wd.register(listener);
            }

            return wd;
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new SkipException("Web-driver error: " + e.getMessage(), e);
        }

    }
}
