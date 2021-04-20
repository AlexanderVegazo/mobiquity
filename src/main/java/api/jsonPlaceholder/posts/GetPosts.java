package api.jsonPlaceholder.posts;

import api.jsonPlaceholder.BaseAPI;

import static io.restassured.RestAssured.given;

public class GetPosts extends BaseAPI {

    String apiPath = "/posts";
    int userId;



    public GetPosts(String baseURI) {
        super(baseURI);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    protected void createRequest() {
        requestSpecBuilder.setBaseUri(baseURI);
        requestSpecBuilder.setBasePath(apiPath);
        requestSpecification = requestSpecBuilder.build();

    }

    @Override
    protected void executeRequest() {
        apiResponse = given().spec(requestSpecification).param("userId", userId).get();

    }



    @Override
    protected void validateResponse() {
        responseSpecBuilder.expectStatusCode(expectedStatusCode);
        responseSpecification = responseSpecBuilder.build();
        apiResponse.then().spec(responseSpecification);

    }
}
