package ru.mail.besfian.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;

public class Specs {
     String body = "{ \"userName\": \"Besfian\", \"password\": \"_Aa%1234\" }";

    public  RequestSpecification requestForBookstoreToolsqa = with()
            .baseUri("https://bookstore.toolsqa.com/Account/v1/")
            .log().all()
            .body(body)
            .contentType(ContentType.JSON);

    public  RequestSpecification requestForDemoqa = with()
            .baseUri("https://demoqa.com/Account/v1/")
            .log().all()
            .body(body)
            .contentType(ContentType.JSON);
}
