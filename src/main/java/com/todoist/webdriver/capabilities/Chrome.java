package com.todoist.webdriver.capabilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

public class Chrome implements DriverCapabilities {

    private final Logger logger = LogManager.getLogger(getClass());

    @Override
    public Capabilities get() {
        ChromeOptions chromeOptions = new ChromeOptions();

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", "/temp");
        chromePrefs.put("download.prompt_for_download", false);

        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.addArguments("--no-sandbox"); // what is sandbox ??

        chromeOptions.merge(getCommonCapabilities());
        return chromeOptions;
    }
}
