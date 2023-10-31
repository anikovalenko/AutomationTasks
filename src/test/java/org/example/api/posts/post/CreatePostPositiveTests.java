package org.example.api.posts.post;

import io.restassured.specification.RequestSpecification;
import org.example.APIs.RequestUtils;
import org.example.APIs.ResponseUtils;
import org.example.APIs.dataproviders.RequestSpecificationProvider;
import org.example.APIs.models.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CreatePostPositiveTests {
    RequestSpecification spec = RequestSpecificationProvider.getRequestSpecification();
    public static final String ENDPOINT = "/posts";
    public static final String PATH_TO_VALIDATOR_FILE = "src/test/resources/apiResourceFiles/validator_schemas/getPostValidatorSchema.json";

    @ParameterizedTest
    @CsvFileSource(resources = "/apiResourceFiles/postValidValue.csv")
    public void validatePostCreation(int id, String title, String author) {
        Post post = new Post(id, title, author);
        String jsonBody = RequestUtils.getJsonStringByObject(post);
        RequestUtils.post(spec, jsonBody, ENDPOINT);
        int statusCode = ResponseUtils.getStatusCode();
        Assertions.assertEquals(201, statusCode);

        RequestUtils.get(spec, id, ENDPOINT);
        ResponseUtils.validateResponseByJsonSchema(PATH_TO_VALIDATOR_FILE);
        Post actualPost = ResponseUtils.getObjectByJsonString(Post.class);
        Assertions.assertEquals(post, actualPost);

        int returnedID1 = ResponseUtils.getObjectByJsonString(Post.class).id;
        String returnedID2 = ResponseUtils.getStringValueByJsonPath("id");
    }
}
