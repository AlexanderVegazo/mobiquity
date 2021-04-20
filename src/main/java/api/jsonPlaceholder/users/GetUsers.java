package api.jsonPlaceholder.users;


import api.jsonPlaceholder.BaseAPI;

import static io.restassured.RestAssured.given;

public class GetUsers extends BaseAPI {

    String apiPath = "/users";
    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public GetUsers(String baseURI) {
        super(baseURI);
    }

    @Override
    protected void createRequest() {
        requestSpecBuilder.setBaseUri(baseURI);
        requestSpecBuilder.setBasePath(apiPath);
        requestSpecification = requestSpecBuilder.build();

    }

    @Override
    protected void executeRequest() {
        apiResponse = given().spec(requestSpecification).param("username", username).get();

    }

    @Override
    protected void validateResponse() {
        responseSpecBuilder.expectStatusCode(expectedStatusCode);
        responseSpecification = responseSpecBuilder.build();
        apiResponse.then().spec(responseSpecification);

    }
}
