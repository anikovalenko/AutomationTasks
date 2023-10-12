package org.example.unit_testing;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class ApiTest {

    @Test
    void getAllUsers() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";

        given()
                .when()
                .get("/users")
                .then()
                .log().all();
    }

    @Test
    void getResponse() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";
        ValidatableResponse response = given()
                .when()
                .get("/users")
                .then();
        int actualCode = response.extract().statusCode();
        Assertions.assertEquals(200, actualCode);
        System.out.println(response.extract().asPrettyString());
    }

    @Test
    void getUserById() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";
        ValidatableResponse response = given()
                .when()
                .get("/users/5313620")
                .then();
        int actualCode = response.extract().statusCode();
        Assertions.assertEquals(200, actualCode);
        System.out.println(response.extract().asPrettyString());
    }

    @Test
    void createUser() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";
        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 5236080e5380ac990496d9e7a50c234eb76ff987094dcf16311957041bc2f6d8")
                .when()
                .body("{\n" +
                        " \"name\": \"Ani\",\n" +
                        " \"email\": \"an1997@gmail.com\" \n" +
                        " \"gender\": \"female\" \n" +
                        " \"status\": \"active\" \n" + "}")
                .post("/users")
                .then().log().ifError();
        int actualCode = response.extract().statusCode();
        Assertions.assertEquals(201, actualCode);
        System.out.println(response.extract().asPrettyString());
    }

    @Test
    void updateUser() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";
        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 13215bdb9d7bfadc608673fe3eda3904c61311ef0f26f8085e0a2256d519cbad")
                .when()
                .body("{\n" +
                        " \"name\": \"updated name\",\n" +
                        " \"email\": \"updated_email@gmail.com\" \n" +
                        " \"gender\": \"male\" \n" +
                        " \"status\": \"inactive\" \n" + "}")
                .put("/users/5313620")
                .then().log().ifError();
        int actualCode = response.extract().statusCode();
        Assertions.assertEquals(201, actualCode);
        System.out.println(response.extract().asPrettyString());
    }

    @Test
    void partialUpdate() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";
        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 13215bdb9d7bfadc608673fe3eda3904c61311ef0f26f8085e0a2256d519cbad")
                .when()
                .body("{\n" +
                        " \"name\": \"Poghosuhi\",\n" + "}")
                .patch("/users/5313620")
                .then().log().ifError();
        int actualCode = response.extract().statusCode();
        Assertions.assertEquals(201, actualCode);
        System.out.println(response.extract().asPrettyString());
    }

}
