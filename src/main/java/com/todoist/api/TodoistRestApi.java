package com.todoist.api;

import com.google.inject.Inject;
import com.todoist.api.resources.ProjectResource;

/**
 * Todoist API
 */
public class TodoistRestApi {

    @Inject
    private ProjectResource project;

    public ProjectResource project() {
        return project;
    }
}
