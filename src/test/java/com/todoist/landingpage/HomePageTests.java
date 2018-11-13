package com.todoist.landingpage;

import com.todoist.BaseTest;
import com.todoist.steps.BrowserSteps;
import com.todoist.steps.NavigationSteps;
import com.todoist.ui.page.homepage.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.inject.Inject;

@Story("MyADT Landing Page")
public class HomePageTests extends BaseTest {

    @Inject
    private NavigationSteps navigationSteps;
    @Inject
    private HomePage homePage;
    @Inject
    private BrowserSteps browserSteps;

    @DataProvider
    public Object[][] helpCenterTopics() {

        return new Object[][]{
                {"Account & Billing", ".*help/billing.*"},
                {"Camera Connection Issues", ".*help/camera.*"},
                {"Help Reading Your Bill", ".*help/your_bill.*"},
                {"Support", ".*help/support.*"}
        };
    }

    @Test(dataProvider = "helpCenterTopics")
    @Description("Verify that user can open Help Center topics.")
    public void userCanResetPasswordByEmail(String helpCenterTopic, String expectedUrlRegexp) {
        navigationSteps.openHomePage();
        homePage.openHelpCenterLink(helpCenterTopic);
        Assertions.assertThat(browserSteps.getCurrentUrl()).matches(expectedUrlRegexp);
    }
}
