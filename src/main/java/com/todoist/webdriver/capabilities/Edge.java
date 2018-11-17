package com.todoist.webdriver.capabilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.edge.EdgeOptions;

public class Edge implements DriverCapabilities {

    private final Logger logger = LogManager.getLogger(getClass());


    @Override
    public Capabilities get() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.merge(getCommonCapabilities());
        return edgeOptions;
    }
}
