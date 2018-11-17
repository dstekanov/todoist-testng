package com.todoist.ui.page.main;

import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TodayPage {

    @Step
    public void addTask(String taskName) {
        $(".agenda_add_task a").click();
        $(".text_box_holder div").val(taskName);
        $(".submit_btn").click();
    }

    @Step
    public void verifyTaskPresent(String taskName) {
        List<String> taskNames = $$(".items .task_item .text").texts();

        Assertions.assertThat(taskNames).containsAnyOf(taskName);
    }
}