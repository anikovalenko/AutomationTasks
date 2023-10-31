package org.example.APIs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public final class RequestUtils {

    private static ValidatableResponse response;

    public static ValidatableResponse getResponse() {
        return response;
    }

    public static void get() {
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .when()
                .get("/posts")
                .then()
                .log().body();
    }

    public static void get(RequestSpecification spec, int id, String endpoint) {
        response = given()
                .when()
                .spec(spec)
                .get(endpoint + "/" + id)
                .then();
    }

    public static void post(RequestSpecification spec, String body, String endpoint) {
        response = given()
                .when()
                .spec(spec)
                .body(body)
                .post(endpoint)
                .then();
    }

    public static String getJsonStringByObject(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString;
        try {
            jsonString = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonString;
    }

}
