package com.demowebshop.tricentis.tests;

import com.codeborne.selenide.WebDriverRunner;
import com.demowebshop.tricentis.helpers.AllureRestAssuredFilter;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.demowebshop.tricentis.tests.TestData.*;
import static io.restassured.RestAssured.given;

public class DemoWebShopTests extends TestBase {

    @Test
    @Feature("Регистрация пользователя и редактирование профиля пользователя c использованием API и проверками в UI")
    @Owner("Kayrat Nurekenov")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Demo Web Shop", url = "https://demowebshop.tricentis.com/")
    @DisplayName("Регистрация пользователя и редактирование профиля пользователя c использованием API и проверками в UI")
    void addToCartWithUiWithAuthTest() {

        String authCookieName = "NOPCOMMERCE.AUTH";

        String authCookieValue = given()
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .contentType("application/x-www-form-urlencoded")
                .cookie(cookieNamePlusValue)
                .formParam(requestVerificationTokenName, requestVerificationTokenValue)
                .formParam("Gender", gender)
                .formParam("FirstName", firstName)
                .formParam("LastName", lastName)
                .formParam("Email", email)
                .formParam("Password", password)
                .formParam("ConfirmPassword", password)
                .when()
                .post("/register")
                .then()
                .statusCode(302)
                .extract()
                .cookie(authCookieName);

        open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/logo.png");
        Cookie authCookie = new Cookie(authCookieName, authCookieValue);
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);

        authorizationPage
                .checkUserCreated(email)
                .openCustomerInfo()
                .checkUserName(firstName)
                .changeUserName(newName)
                .openCustomerInfo()
                .checkUserName(newName);
    }
}
