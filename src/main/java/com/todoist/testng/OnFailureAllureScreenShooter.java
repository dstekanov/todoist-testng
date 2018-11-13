package com.todoist.testng;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.testng.ScreenShooter;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class OnFailureAllureScreenShooter extends ScreenShooter {

    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
        File lastScreenshot = Screenshots.getLastScreenshot();
        if (Objects.nonNull(lastScreenshot)) {
            try {
                attachScreenShot(lastScreenshot);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Attachment(type = "image/png")
    public static byte[] attachScreenShot(File file) throws IOException {
        return Files.toByteArray(file);
    }
}
