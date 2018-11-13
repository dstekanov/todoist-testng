package com.todoist;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.todoist.guice.MainModule;
import com.todoist.reporting.WebDriverLogger;
import com.todoist.testng.OnFailureAllureScreenShooter;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Listeners;

import javax.inject.Inject;
import javax.inject.Named;

@Listeners({
        OnFailureAllureScreenShooter.class
})
@Guice(modules = {MainModule.class})
public class BaseTest {

    @Inject
    @Named("browser")
    protected String browser;

    @BeforeClass
    public void setUp() {
        // -Dchromeoptions.args

        // TODO: write separate module for capabilities (support for different browsers)

        if (browser.equals("edge"))
            System.setProperty("wdm.edgeVersion", "4.15063"); // OS version supported: 15.15063

        Configuration.browser = browser;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        capabilities.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        Configuration.browserCapabilities = capabilities;
        Configuration.startMaximized = true;

        WebDriverRunner.addListener(new WebDriverLogger());
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

}
