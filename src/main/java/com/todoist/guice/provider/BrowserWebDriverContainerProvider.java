package com.todoist.guice.provider;

import com.todoist.utils.Config;
import com.todoist.webdriver.Browser;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testcontainers.containers.BrowserWebDriverContainer;

import javax.inject.Provider;

public class BrowserWebDriverContainerProvider implements Provider<BrowserWebDriverContainer> {

    @Override
    public BrowserWebDriverContainer get() {

        BrowserWebDriverContainer container =
                new BrowserWebDriverContainer()
                        .withDesiredCapabilities(chooseBrowser().merge(commonCapabilities()));

        return container;
    }

    private DesiredCapabilities chooseBrowser() {
        if (Config.getBrowser() == Browser.FIREFOX) {
            return DesiredCapabilities.firefox();
        }
        return DesiredCapabilities.chrome();
    }

    private DesiredCapabilities commonCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        capabilities.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return capabilities;
    }
}
