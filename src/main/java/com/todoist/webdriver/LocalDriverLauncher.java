package com.todoist.webdriver;


import com.todoist.utils.Config;
import com.todoist.webdriver.local.LocalBrowser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class LocalDriverLauncher implements WebDriverLauncher {

    private final Logger logger = LogManager.getLogger(getClass());

    @Override
    public WebDriver start() {
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

        RemoteWebDriver driver = LocalBrowser.driverFor(browser);

        // HOW IT WORKS ??
        driver.manage().window().maximize();

        // Augmented driver adds support for extension with interfaces (like mixins) based on driver
        // For example: ??? ~ Web Storage
        Augmenter augmenter = new Augmenter();
        WebDriver augmentedDriver = augmenter.augment(driver);

        // Create EventFiringWebDriver to support logging purposes throw wd.register(WebDriverEventListener)
        EventFiringWebDriver eventFiringWD = new EventFiringWebDriver(augmentedDriver);

        return eventFiringWD;
    }
}
