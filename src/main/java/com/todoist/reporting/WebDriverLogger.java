package com.todoist.reporting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class WebDriverLogger extends AbstractWebDriverEventListener {

    private final Logger logger = LogManager.getLogger(getClass());

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        logger.trace(String.format("Locating element %s", by));
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        logger.trace(String.format("Locator [%s] has been found", by));
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        logger.trace(String.format("Clicking on [%s]", element));
    }

}
