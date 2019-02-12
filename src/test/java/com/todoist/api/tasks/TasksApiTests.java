package com.todoist.api.tasks;

import com.todoist.api.BaseRestTest;
import com.todoist.dto.rest.Task;
import io.qameta.allure.Description;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;

public class TasksApiTests extends BaseRestTest {

    @Test
    @Description("Request all active tasks.")
    public void requestProjects() {
        List<Task> tasks = todoistRestSteps.getActiveTasks();

        Assertions.assertThat(tasks).size().isGreaterThan(0);
    }

}
