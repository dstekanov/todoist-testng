package com.todoist.landingpage;

import com.todoist.BaseTest;
import com.todoist.dto.data.UserDTO;
import com.todoist.steps.NavigationSteps;
import com.todoist.ui.page.homepage.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.inject.Named;

@Story("Login Tests")
public class LoginTests extends BaseTest {

    @Inject
    private NavigationSteps navigationSteps;
    @Inject
    private LoginPage loginPage;
    @Inject
    @Named("user_regular")
    private UserDTO user;

    @Test
    @Description("Verify that user can login.")
    public void userCanLogin() {
        navigationSteps.openLoginPage();

        loginPage
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .clickLoginButton();

    }

}
