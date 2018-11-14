package com.todoist;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.todoist.guice.MainModule;
import com.todoist.reporting.WebDriverLogger;
import com.todoist.testng.OnFailureAllureScreenShooter;
import io.qameta.allure.selenide.AllureSelenide;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Listeners;

import javax.inject.Inject;

@Listeners({
        OnFailureAllureScreenShooter.class
})
@Guice(modules = {MainModule.class})
public class BaseTest {

    @Inject
    private BrowserWebDriverContainer container;

    @BeforeClass
    public void start() {
        container.start();
        WebDriverRunner.setWebDriver(container.getWebDriver());

        WebDriverRunner.addListener(new WebDriverLogger());
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterClass(alwaysRun = true)
    public void stop() {
        WebDriverRunner.closeWebDriver();
        container.stop();
    }

}
