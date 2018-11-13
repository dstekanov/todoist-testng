package com.todoist.rest;

import com.todoist.api.TodoistRestSteps;
import com.todoist.guice.MainModule;
import org.testng.annotations.Guice;

import javax.inject.Inject;

@Guice(modules = {MainModule.class})
public class BaseRestTest {

    @Inject
    TodoistRestSteps todoistRestSteps;
}
