package com.todoist.api.resources;

import com.todoist.dto.rest.Project;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

public class ProjectResource extends BaseResource {

    /**
     * GET /API/v8/projects/{projectKey}
     */
    public Project get(String projectKey) {
        Response response = newRequest()
                .get("projects/{projectKey}", projectKey);

        return response.as(Project.class);
    }

    /**
     * GET /API/v8/projects
     */
    public List<Project> get() {
        Response response = newRequest()
                .get("projects");

        return Arrays.asList(response.as(Project[].class));
    }

    /**
     * GET /API/v8/projects
     */
    public Response getResponse() {
        return newRequest()
                .get("projects");
    }
}
