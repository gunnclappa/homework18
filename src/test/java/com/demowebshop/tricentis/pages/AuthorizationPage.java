package com.demowebshop.tricentis.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

import static com.codeborne.selenide.Selenide.open;

public class AuthorizationPage {

    private final SelenideElement
            openProfileLink = $(".account"),
            registrationResult = $(".result"),
            headerLogin = $(".account"),
            firstName = $("#FirstName"),
            saveButton = $(".save-customer-info-button");


    @Step("Проверка создания пользователя с email {value}")
    public AuthorizationPage checkUserCreated(final String value) {
        open("/registerresult/1");
        openProfileLink.shouldHave(text(value));
        registrationResult.shouldHave(text("Your registration completed"));

        return this;
    }

    @Step("Открытие страницы с информацией о пользователе")
    public AuthorizationPage openCustomerInfo() {
        headerLogin.click();

        return this;
    }

    @Step("Изменение имени пользователя на {value}")
    public AuthorizationPage changeUserName(final String value) {
        firstName.setValue(value);
        saveButton.click();

        return this;
    }

    @Step("Проверка имени пользователя")
    public AuthorizationPage checkUserName(final String value) {
        firstName.shouldHave(Condition.value(value));

        return this;
    }
}

