package com.todoist.guice.module;

import com.google.inject.AbstractModule;
import com.todoist.utils.Config;
import com.todoist.webdriver.LocalDriverConfiguration;
import com.todoist.webdriver.RemoteDriverConfiguration;
import com.todoist.webdriver.WebDriverConfiguration;

public class WebDriverModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(WebDriverConfiguration.class).to(chooseDriver()).asEagerSingleton();
    }

    private Class<? extends WebDriverConfiguration> chooseDriver() {
        return Config.isGridUse()
                ? RemoteDriverConfiguration.class
                : LocalDriverConfiguration.class;
    }

}
