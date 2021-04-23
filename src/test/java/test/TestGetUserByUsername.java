package test;

import api.jsonPlaceholder.Comments.GetComments;
import api.jsonPlaceholder.posts.GetPosts;
import api.jsonPlaceholder.users.GetUsers;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pojo.jsonPlaceholder.comments.Comment;
import pojo.jsonPlaceholder.posts.Post;
import pojo.jsonPlaceholder.users.User;

import java.io.IOException;

public class TestGetUserByUsername {

    public static final Logger logger = Logger.getLogger(TestGetUserByUsername.class);

    String baseURI;

    final int httpOkStatusCode = 200;

    @BeforeTest
    public void prepare() {
        baseURI = "https://jsonplaceholder.typicode.com";

    }

    int DelphineUserID;
    Post[] posts;


    @Test
    public void findUserByUsername() throws IOException {


        GetUsers getUsers = new GetUsers(baseURI);
        getUsers.setUsername("Delphine");
        getUsers.setExpectedStatusCode(httpOkStatusCode);
        getUsers.perform();
        assert (getUsers.getApiResponseAsString().contains("Delphine"));
        logger.info("Get users cleared successfully");
        User[] users = getUsers.getAPIResponseAsPOJO(User[].class);
        DelphineUserID = users[0].getId();




        //   ArrayList<Comment> comments = new ArrayList<>();




    }

    @Test(dependsOnMethods = { "findUserByUsername" })
    public void findPostByUserId () throws IOException {

        GetPosts getPosts = new GetPosts(baseURI);
        getPosts.setUserId(DelphineUserID);
        getPosts.setExpectedStatusCode(httpOkStatusCode);
        getPosts.perform();
        logger.info("Get posts cleared successfully");
        posts = getPosts.getAPIResponseAsPOJO(Post[].class);

        assert posts.length > 0;


    }

    @Test(dependsOnMethods = { "findPostByUserId" })
    public void validateEmailsFormatInComments() throws IOException {

        for (Post post : posts) {

            GetComments getComments = new GetComments(baseURI);
            getComments.setPostId(post.getId());
            getComments.setExpectedStatusCode(httpOkStatusCode);
            getComments.perform();
            logger.info("Get Comments cleared successfully");
            Comment[] comments = getComments.getAPIResponseAsPOJO(Comment[].class);


            for (Comment comment : comments) {

                assert EmailValidator.getInstance().isValid(comment.getEmail());

            }


        }








    }
}
