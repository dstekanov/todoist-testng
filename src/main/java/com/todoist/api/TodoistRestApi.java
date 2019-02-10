package com.todoist.api;

import com.google.inject.Inject;
import com.todoist.api.resources.ProjectResource;
import com.todoist.api.resources.TasksResource;

/**
 * Todoist API
 */
public class TodoistRestApi {

    @Inject
    private ProjectResource project;

    @Inject
    private TasksResource tasks;

    public ProjectResource project() {
        return project;
    }

    public TasksResource tasks() {
        return tasks;
    }
}
