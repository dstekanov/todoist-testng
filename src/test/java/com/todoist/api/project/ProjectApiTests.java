package com.todoist.api.project;

import com.todoist.api.BaseRestTest;
import com.todoist.dto.rest.Project;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class ProjectApiTests extends BaseRestTest {

    @Test
    @Description("Request all existing projects")
    public void requestProjects() {
        List<Project> projects = todoistRestSteps.getProjects();

        Assertions.assertThat(projects).size().isGreaterThan(0);
    }

    @Test
    @Description("Request all existing projects and return JsonPath (XPath for JSON)")
    public void requestProjectsJsonPath() {
        JsonPath projects = todoistRestSteps.getProjectsResponse().jsonPath();

        List<String> projectsNames = projects.get("name");
        System.out.println("Projects names: " + projectsNames);

        List<Map> projects1 = projects.get("findAll { it -> it.indent == 1}");
        List<String> projects2 = projects.get("findAll { it -> it.indent == 1}");

        System.out.println(projects1);
        System.out.println(projects2);
    }


}
