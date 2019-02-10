package com.todoist.api.resources;

import com.todoist.dto.rest.Project;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

public class ProjectResource extends BaseResource {

    private static final String PROJECTS_ENDPOINT = "projects";

    /**
     * GET /API/v8/projects/{projectKey}
     */
    public Project get(String projectKey) {
        Response response = newRequest()
                .get(String.format("%s/{projectKey}", PROJECTS_ENDPOINT), projectKey);

        return response.as(Project.class);
    }

    /**
     * GET /API/v8/projects
     */
    public List<Project> get() {
        Response response = newRequest()
                .get(PROJECTS_ENDPOINT);

        return Arrays.asList(response.as(Project[].class));
    }

    /**
     * GET /API/v8/projects
     */
    public Response getFullProjectsResponse() {
        return newRequest()
                .get(PROJECTS_ENDPOINT);
    }
}
