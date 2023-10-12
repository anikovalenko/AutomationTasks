package org.example.APIs;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public final class RequestUtils {

    private static ValidatableResponse response;

    public static ValidatableResponse getResponse() {
        return response;
    }

    public static void get() {
        RestAssured.baseURI = "http://localhost:3000";
        response = given().when().get("/posts").then().log().body();
    }

//    public static void post() {
//        response =
//    }
}
