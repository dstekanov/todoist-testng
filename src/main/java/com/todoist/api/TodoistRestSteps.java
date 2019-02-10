package com.todoist.api;

import com.google.inject.Inject;
import com.todoist.dto.rest.Project;
import com.todoist.dto.rest.Task;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

/**
 * API useful steps
 */
public class TodoistRestSteps {

    @Inject
    private TodoistRestApi restApi;

    @Step
    public List<Project> getProjects() {

        return restApi.project().get();
    }

    @Step
    public Response getProjectsResponse() {

        return restApi.project().getFullProjectsResponse();
    }

    @Step
    public List<Task> getActiveTasks() {

        return restApi.tasks().get();
    }

}
