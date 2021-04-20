package api.jsonPlaceholder.Comments;

import api.jsonPlaceholder.BaseAPI;

import static io.restassured.RestAssured.given;

public class GetComments extends BaseAPI {

    String apiPath = "/comments";
    int postId;



    public GetComments(String baseURI) {
        super(baseURI);
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }


    @Override
    protected void createRequest() {
        requestSpecBuilder.setBaseUri(baseURI);
        requestSpecBuilder.setBasePath(apiPath);
        requestSpecification = requestSpecBuilder.build();

    }

    @Override
    protected void executeRequest() {
        apiResponse = given().spec(requestSpecification).param("postId", postId).get();

    }

    @Override
    protected void validateResponse() {
        responseSpecBuilder.expectStatusCode(expectedStatusCode);
        responseSpecification = responseSpecBuilder.build();
        apiResponse.then().spec(responseSpecification);

    }
}
