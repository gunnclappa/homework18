package com.demowebshop.tricentis.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demowebshop.tricentis.config.Project;
import com.demowebshop.tricentis.config.demowebshop.App;
import com.demowebshop.tricentis.helpers.AllureAttachments;
import com.demowebshop.tricentis.helpers.DriverSettings;
import com.demowebshop.tricentis.helpers.DriverUtils;
import com.demowebshop.tricentis.pages.AuthorizationPage;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({AllureJunit5.class})
public class TestBase {

    AuthorizationPage authorizationPage = new AuthorizationPage();

    @BeforeAll
    static void beforeAll() {
        DriverSettings.configure();
        RestAssured.baseURI = App.config.apiUrl();
        Configuration.baseUrl = App.config.webUrl();
    }

    @BeforeEach
    public void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    public void afterEach() {
        String sessionId = DriverUtils.getSessionId();

        AllureAttachments.addScreenshotAs("Last screenshot");
        AllureAttachments.addPageSource();
//        AllureAttachments.attachNetwork(); // todo
        AllureAttachments.addBrowserConsoleLogs();

        if (Project.isVideoOn()) {
            AllureAttachments.addVideo(sessionId);
        }

        Selenide.closeWebDriver();
    }
}