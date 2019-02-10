package com.todoist.api;

import com.todoist.guice.MainModule;
import org.testng.annotations.Guice;

import javax.inject.Inject;

@Guice(modules = {MainModule.class})
public class BaseRestTest {

    @Inject
    protected TodoistRestSteps todoistRestSteps;
}
