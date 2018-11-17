package com.todoist.ui.page.loginpage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ForgotPasswordWidget {

    private SelenideElement root = $(".login-component .myadt-form");

    private SelenideElement byEmail = root.$(byText("Email"));
    private SelenideElement bySecurityQuestion = root.$(byText("Security Question"));
    private SelenideElement verifyButton = root.$(byText("Verify"));
    private SelenideElement emailInput = root.$("input[type=email]");
    private SelenideElement continueButton = root.$(byText("Continue"));
    private SelenideElement securityQuestion = $("input[type=text]");
    private SelenideElement newPasswordInput = $(by("placeholder", "New Password"));
    private SelenideElement confirmNewPasswordInput = $(by("placeholder", "Confirm Password"));


    @Step
    public void resetPasswordByEmail(String email) {
        byEmail.click();
        emailInput.val(email);
        verifyButton.click();
    }

    @Step
    public void resetPasswordBySecurityQuestion(String email, String securityQuestion, String newPassword) {
        bySecurityQuestion.click();
        emailInput.val(email);
        verifyButton.click();
        this.securityQuestion.val(securityQuestion);
        continueButton.click();
        newPasswordInput.val(newPassword);
        confirmNewPasswordInput.val(newPassword);
        continueButton.click();
    }

}
