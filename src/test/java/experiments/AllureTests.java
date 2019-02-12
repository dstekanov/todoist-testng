package experiments;

import io.qameta.allure.model.Status;
import io.qameta.allure.util.ObjectUtils;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static io.qameta.allure.Allure.*;

public class AllureTests {

    @Test
    public void simpleTest() {
        // Add test links
        link("testing", "https://example.com");
        issue("GH-123", "https://github.com/allure-framework/allure2/issues/123");
        tms("AS-182", "https://allureee.qameta.io/project/1/test-cases/182");

        // Add test labels
        epic("Allure Java API");
        feature("Dynamic API");
        label("component", "allure-java-commons");

        // Add parameters to test within test body
        String baseUrl = parameter("baseUrl", "https://example.com/getData");

        // Log-style steps
        step("set up test database", Status.SKIPPED);
        step("set up test create mocks"); // Status.PASSED by default

        // Add parameters to test inside steps as well
        String token = step("authorization", () -> {
            String login = parameter("login", "admin");
            String password = parameter("password", "admin");
            return getAuth(login, password);
        });

        // Add parameters to step using injected StepContext
        step("preparation checks", (step) -> {
            step.parameter("a", "a value");
            step.parameter("b", "b value");
        });

        // Nested step and dynamic step name
        step((step) -> {
            String a = step("child 1", () -> "A");
            String b = step("child b", () -> "B");
            String c = step("child b", () -> "C");

            step.name("dynamic name " + a + b + c);
        });

        // Create attachments as well as steps
        step("get data", () -> {
            step("build client");
            List<String> responseData = step("run request", (step) -> {
                step.parameter("authorization", token);
                step.parameter("url", baseUrl);
                int[] requestBody = step.parameter("requestBody", new int[]{1, 2, 3});

                return getData(baseUrl, token, requestBody);
            });
            attachment("response", ObjectUtils.toString(responseData));
        });
    }

    List<String> getData(final String url, final String token, final Object body) {
        return Collections.emptyList();
    }

    String getAuth(final String login, final String password) {
        return String.format("Basic %s:%s", login, password);

    }
}
