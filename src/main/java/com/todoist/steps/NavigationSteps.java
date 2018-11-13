package com.todoist.steps;

import com.todoist.utils.Config;
import io.qameta.allure.Step;

import javax.inject.Inject;
import javax.inject.Named;

public class NavigationSteps {

    @Inject
    @Named("site_protocol")
    private String defaultProtocol;
    @Inject
    private BrowserSteps browserSteps;

    private String baseUrl() {
        return String.format("%s://%s", defaultProtocol, Config.getSiteUrl());
    }

    @Step("Open Home Page")
    public void openHomePage() {
        browserSteps.openPath(baseUrl(), "");
    }

    @Step("Open Login Page")
    public void openLoginPage() {
        browserSteps.openPath(baseUrl(), "/users/showlogin");
    }

}
