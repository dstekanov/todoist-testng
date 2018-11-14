package com.todoist.webdriver.capabilities;

import com.todoist.webdriver.Browser;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.function.Supplier;

import static org.openqa.selenium.UnexpectedAlertBehaviour.ACCEPT;
import static org.openqa.selenium.UnexpectedAlertBehaviour.DISMISS;
import static org.openqa.selenium.remote.CapabilityType.*;

public interface DriverCapabilities extends Supplier<Capabilities> {

    static DriverCapabilities capabilitiesFor(Browser browser) {
        switch (browser) {
            case FIREFOX:
                return new FireFox();
            case EDGE:
                return new Edge();
            case IE:
                return new IE();
            case CHROME:
                return new Chrome();
            default:
                throw new IllegalArgumentException(String.format("Not supported browser = [%s]", browser.getName()));
        }
    }

    default Capabilities getCommonCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(UNEXPECTED_ALERT_BEHAVIOUR, ACCEPT);
        capabilities.setCapability(UNHANDLED_PROMPT_BEHAVIOUR, DISMISS);
        capabilities.setCapability(ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(ACCEPT_INSECURE_CERTS, true);
        return capabilities;
    }

}
