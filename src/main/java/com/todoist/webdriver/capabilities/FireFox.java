package com.todoist.webdriver.capabilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class FireFox implements DriverCapabilities {

    private final Logger logger = LogManager.getLogger(getClass());

    @Override
    public Capabilities get() {
        System.setProperty("webdriver.firefox.logfile", "log\\browsers\\firefox.log");

        FirefoxProfile profile = new FirefoxProfile();

//                profile.setPreference("security.mixed_content.block_active_content", false); // disable mixed content warn
//                profile.setPreference("plugin.state.flash", 2); // 0=disable, 1=ask user, 2 = enable
//                profile.setPreference("network.proxy.http", "localhost");
//                profile.setPreference("network.proxy.http_port", 3128);
//                profile.setPreference("network.proxy.ssl", "proxy.domain.example.com");
//                profile.setPreference("network.proxy.ssl_port", 3128);
//              profile.setAcceptUntrustedCertificates(true);
//				profile.setEnableNativeEvents(true);

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments(
//                        "-devtools"
//                        ,"-jsconsole"
        );

        firefoxOptions.setProfile(profile);
        firefoxOptions.merge(getCommonCapabilities());

        return firefoxOptions;
    }
}
