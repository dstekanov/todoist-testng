package com.todoist.ui.login;

import com.todoist.dto.data.User;
import com.todoist.steps.NavigationSteps;
import com.todoist.ui.BaseTest;
import com.todoist.ui.page.loginpage.LoginPage;
import com.todoist.ui.page.main.TodayPage;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.inject.Named;

import static io.qameta.allure.Allure.parameter;

@Story("Login Tests")
public class LoginTests extends BaseTest {

    @Inject
    private NavigationSteps navigationSteps;
    @Inject
    private LoginPage loginPage;
    @Inject
    private TodayPage todayPage;
    @Inject
    @Named("user_regular")
    private User user;

    @Test
    @Description("Verify that user can login.")
    public void userCanLogin() {
        navigationSteps.openLoginPage();

        loginPage
                .setEmail(parameter("email", user.getEmail()))
                .setPassword(parameter("password", user.getPassword()))
                .clickLoginButton();
        todayPage.verifyOpened();
    }

}
