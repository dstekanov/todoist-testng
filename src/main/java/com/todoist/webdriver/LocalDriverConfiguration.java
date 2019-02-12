package com.todoist.webdriver;


import com.codeborne.selenide.Configuration;
import com.todoist.utils.Config;
import com.todoist.webdriver.capabilities.DriverCapabilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

public class LocalDriverConfiguration implements WebDriverConfiguration {

    private final Logger logger = LogManager.getLogger(getClass());

    @Override
    public void configure() {
        final Browser browser = Config.getBrowser();
        logger.info(String.format("Starting Local WebDriver. Browser==[%s]...", browser.getName()));

        // region =========== Browser logs ================================
        // LoggingPreferences logPrefs = new LoggingPreferences();
        // Chrome : https://sites.google.com/a/chromium.org/chromedriver/logging/performance-log
        // logPrefs.enable(LogType.PERFORMANCE, Level.WARNING);    // works only for Chrome
        // driver.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        /* chrome tracing */
        // Map<String, Object> perfLogPrefs = new HashMap<>();
        // perfLogPrefs.put("traceCategories", "netlog");
        // comma-separated trace: "browser,devtools.timeline,devtools"

        // ChromeOptions options = new ChromeOptions();
        // options.setExperimentalOption("perfLoggingPrefs", perfLogPrefs);
        // driver.setCapability(ChromeOptions.CAPABILITY, options);

        //endregion =========== Browser logs ================================
        //region =========== BrowserMob proxy ================================
//        Proxy proxy = getProxy();
//        if (proxy != null) driver.setCapability(CapabilityType.PROXY, proxy);
        // endregion =========== BrowserMob proxy ================================

        Configuration.browser = browser.getName();
        Configuration.headless = true;

        Configuration.browserCapabilities = new DesiredCapabilities(DriverCapabilities.capabilitiesFor(browser).get());
    }
}
