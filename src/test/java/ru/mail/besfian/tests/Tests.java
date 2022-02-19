package ru.mail.besfian.tests;


import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import ru.mail.besfian.lombok.UserResponseData;
import ru.mail.besfian.specs.Specs;



import static io.restassured.RestAssured.given;

import static jdk.internal.dynalink.support.Guards.isNotNull;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.*;

public class Tests extends TestBase {

    Specs specs = new Specs();
    TestData testData = new TestData();

    @Test
    void testAuthorized() {
        given().
                spec(specs.requestForBookstoreToolsqa)
                .when()
                .post("/Authorized")
                .then().log().all().statusCode(200).body(is("true"));
    }


    @Test
    void testGenerateToken() {
        Response response = given().
                spec(specs.requestForBookstoreToolsqa)
                .when()
                .post("/GenerateToken")
                .then().log().all().statusCode(200).extract().response();
        assertThat(response.body().path("status").equals("Success")).isTrue();
        assertThat(response.body().path("result").equals("User authorized successfully.")).isTrue();
    }

    @Test
    void testAccountLogin() {
        Response response = given().
                spec(specs.requestForBookstoreToolsqa)
                .when()
                .post("/Login")
                .then().log().all().statusCode(200).extract().response();
        assertThat(response.body().path("userId").equals("bf9a63b1-710c-4b58-9a76-55d879e4e7ce")).isTrue();
        assertThat(response.body().path("token").equals(isNotNull()));


    }

    @Test
    void testAccountUser() {
        String response = given().
                spec(specs.requestForDemoqa)
                .headers("Authorization", "Bearer " + setup().getToken())
                .when()
                .get("/User/" + setup().getUserId())
                .then().log().all().statusCode(200).extract().asString();
        assertThat(response).isEqualTo(testData.responseAccountUser);

    }

}
