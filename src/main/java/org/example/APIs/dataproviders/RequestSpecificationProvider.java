package org.example.APIs.dataproviders;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationProvider {

    static RequestSpecification requestSpecification;

    public static RequestSpecification getRequestSpecification() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://localhost:3000")
                .build()
                .contentType(ContentType.JSON);
        return requestSpecification;
    }
}
