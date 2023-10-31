package org.example.api.posts.get;

import io.restassured.response.ValidatableResponse;
import org.example.APIs.RequestUtils;
import org.example.APIs.ResponseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class getAllPostsPositiveTest {
    public static final String PATH_TO_VALIDATOR_FILE =
            "src/test/resources/apiResourceFiles/validator_schemas/getAllPostsValidatorSchema.json";

    @Test
    public void validateStatusCode() {
        RequestUtils.get();
        int result = ResponseUtils.getStatusCode();
        Assertions.assertEquals(200, result);
    }

    @Test
    public void validateResponseByJsonSchema() {
        RequestUtils.get();
        ResponseUtils.validateResponseByJsonSchema(PATH_TO_VALIDATOR_FILE);

    }
}
