package com.todoist.ui.page.homepage;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    @Step
    public LoginPage setEmail(String email) {
        $("#email").val(email);
        return this;
    }

    @Step
    public LoginPage setPassword(String password) {
        $("#password").val(password);
        return this;
    }

    @Step
    public void clickLoginButton() {
        $(".submit_btn").click();
        // return next page ?
    }

    @Step
    public LoginPage clickForgotPasswordLink() {
        $("a[href$='forgotPassword']").click();
        return this; // return next page ?
    }

    @Step
    public LoginPage clickRegisterNowLink() {
        $(By.partialLinkText("register")).click();
        return this;
    }

    @Step
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

}
