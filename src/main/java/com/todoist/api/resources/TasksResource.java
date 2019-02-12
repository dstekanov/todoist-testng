package com.todoist.api.resources;

import com.todoist.dto.rest.Task;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

public class TasksResource extends BaseResource {

    private static final String TASKS_ENDPOINT = "tasks";

    /**
     * GET /API/v8/tasks/
     */
    public List<Task> get(String projectId) {
        final Response response = newRequest().param("project_id", projectId)
                .get(TASKS_ENDPOINT);

        return Arrays.asList(response.as(Task[].class));
    }

    /**
     * GET /API/v8/projects
     */
    public List<Task> get() {
        final Response response = newRequest()
                .get(TASKS_ENDPOINT);

        return Arrays.asList(response.as(Task[].class));
    }

}
