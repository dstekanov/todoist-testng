package com.todoist.steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;

public class BrowserSteps {

    private final Logger logger = LogManager.getLogger(getClass());

    @Step
    public void openUrl(String url) {
        logger.info(String.format("Open URL...: [%s]", url));
        WebDriverRunner.clearBrowserCache();
        Selenide.open(url);
    }

    @Step
    public void openUrl(URL url) {
        logger.info(String.format("Open URL...: [%s]", url));
        WebDriverRunner.clearBrowserCache();
        Selenide.open(url);
    }

    public void openPath(String protocol, String baseUrl, String path) {
        String base = String.format("%s://%s", protocol, baseUrl);
        openPath(base, path);
    }

    public void openPath(String baseUrl, String path) {
        String url = (baseUrl.endsWith("/") || path.startsWith("/"))
                ? baseUrl + path
                : baseUrl + "/" + path;
        openUrl(url);
    }

    public String getCurrentUrl() {
        return WebDriverRunner.url();
    }

}
