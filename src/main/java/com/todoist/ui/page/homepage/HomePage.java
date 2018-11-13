package com.todoist.ui.page.homepage;

import io.qameta.allure.Step;

import javax.inject.Inject;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    @Inject
    public LoginPage loginPage;
    @Inject
    public ForgotPasswordWidget forgotPasswordWidget;

    @Step
    public void helpSearch(String keyword) {
        $(by("placeholder", "Type your question or keywords")).val(keyword);
        $(".search-icon").click();
    }

    @Step
    public void openHelpCenterLink(String helpCenterTopic) {
        $(".cta").$(byText(helpCenterTopic)).
                $x("./parent::div//a")
                .click();
    }

    @Step
    public void clickMakeOneTimePaymentLink() {
        $(byText("Make a One-Time Express Payment")).click();
    }

}
