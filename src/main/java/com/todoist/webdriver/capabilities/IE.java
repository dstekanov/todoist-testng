package com.todoist.webdriver.capabilities;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import static org.openqa.selenium.remote.CapabilityType.ACCEPT_INSECURE_CERTS;

public class IE implements DriverCapabilities {

    @Override
    public Capabilities get() {
        System.setProperty("webdriver.ie.driver.logfile", "log\\browsers\\ie.log");

        InternetExplorerOptions ieOptions = new InternetExplorerOptions();
        ieOptions.merge(getCommonCapabilities());
        // solution for problem with cookies (and another session items) shared between multiple instances of InternetExplorer.
        ieOptions.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        // the IE driver does not allow bypassing insecure (self-signed) SSL certificates
        ieOptions.setCapability(ACCEPT_INSECURE_CERTS, false);
        return ieOptions;
    }
}
