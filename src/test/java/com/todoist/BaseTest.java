package com.todoist;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.todoist.guice.MainModule;
import com.todoist.testng.OnFailureAllureScreenShooter;
import com.todoist.webdriver.WebDriverConfiguration;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Guice;
import org.testng.annotations.Listeners;

import javax.inject.Inject;

@Listeners({
        OnFailureAllureScreenShooter.class
})
@Guice(modules = {MainModule.class})
public class BaseTest {

    @Inject
    private WebDriverConfiguration driver;

    @BeforeSuite
    public void start() {
        driver.configure();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterClass(alwaysRun = true)
    public void stop() {
        WebDriverRunner.closeWebDriver();
    }

}
