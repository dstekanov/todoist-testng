package com.todoist.webdriver;

import com.codeborne.selenide.Configuration;
import com.todoist.utils.Config;
import com.todoist.webdriver.capabilities.DriverCapabilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

public class RemoteDriverConfiguration implements WebDriverConfiguration {

    private final Logger logger = LogManager.getLogger(getClass());

    @Override
    public void configure() {
        final Browser browser = Config.getBrowser();
        logger.info(String.format("Starting Remote WebDriver. Browser==[%s]...", browser));

        Capabilities capabilities = DriverCapabilities.capabilitiesFor(browser).get();

        DesiredCapabilities selenoidCapabilities = new DesiredCapabilities(capabilities);
        selenoidCapabilities.setCapability("enableVNC", true);
        selenoidCapabilities.setCapability("enableVideo", true);

        Configuration.remote = "http://" + Config.getGridHost() + "/wd/hub";
        Configuration.browserCapabilities = selenoidCapabilities;
        Configuration.browserSize = "1920x768";
    }
}
