package ru.mail.besfian.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import ru.mail.besfian.lombok.UserResponseData;
import ru.mail.besfian.specs.Specs;


import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class TestBase {
    public  UserResponseData USER_RESPONSE_DATA = new UserResponseData();


    Specs specs = new Specs();


    public UserResponseData setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        step("Получение userId", () -> {
            UserResponseData userResponseData = given().
                    spec(specs.requestForBookstoreToolsqa)
                    .when()
                    .post("/Login")
                    .then().log().all().statusCode(200).extract().as(UserResponseData.class);
            USER_RESPONSE_DATA.setUserId(userResponseData.getUserId());
            USER_RESPONSE_DATA.setToken(userResponseData.getToken());


        });
        return USER_RESPONSE_DATA;
    }

}

