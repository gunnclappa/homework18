package com.demowebshop.tricentis.tests;

import com.demowebshop.tricentis.helpers.AllureRestAssuredFilter;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        authorizationPage
                .setCookie(authCookieName, authCookieValue)
                .checkUserCreated(email)
                .openCustomerInfo()
                .checkUserName(firstName)
                .changeUserName(newName)
                .openCustomerInfo()
                .checkUserName(newName);
    }
}
