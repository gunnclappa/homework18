package com.demowebshop.tricentis.tests;

import com.github.javafaker.Faker;

public class TestData {
    static Faker faker = new Faker();
    public static String
            gender = "M",
            firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = "MyTestEmailForHomework2@gmail.com",
            password = "123123",
            requestVerificationTokenName = "__RequestVerificationToken",
            requestVerificationTokenValue = "wQEf6CmG7UqxQUr6YR1yFfL03TmnLm74Gl_L34Nm0p8vWqcR-UjxFi7y6-JuoUO3Ye8nO_EOpDUi62BxtqJsgOcbEkE81gbLe4Z8aQlIRSU1",
            cookieNamePlusValue = "__RequestVerificationToken=E3ESxQaXefwXI3tVnCALB15W06XMsBd3oMDTOldJJKyhazKvuc1_IxuCETMmWiFDBTxJrQmDo8rwC7xsoG2IA7dUiNwy9dsMzHhV2V42jbE1;",
            newName = "NewName";
}