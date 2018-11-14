package com.todoist.guice.module;

import com.google.inject.AbstractModule;
import com.todoist.guice.provider.BrowserWebDriverContainerProvider;
import org.testcontainers.containers.BrowserWebDriverContainer;

public class WebDriverModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(BrowserWebDriverContainer.class).toProvider(BrowserWebDriverContainerProvider.class);
    }

}
