package com.jsonplaceholder.tests;

import com.jsonplaceholder.config.BaseTest;
import com.jsonplaceholder.models.Post;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

/**
 * Testes para o endpoint GET /posts
 */
public class PostsTest extends BaseTest {

    @Test
    public void testGetAllPosts() {
        // Act
        Response response = given()
                .spec(getRequestSpecification())
                .when()
                .get("/posts");

        // Assert
        assertEquals(200, response.getStatusCode());
        
        List<Post> posts = response.jsonPath().getList(".", Post.class);
        assertTrue(posts.size() > 0);
        
        // Validar primeiro post
        Post firstPost = posts.get(0);
        assertNotNull(firstPost.getUserId());
        assertNotNull(firstPost.getId());
        assertNotNull(firstPost.getTitle());
        assertNotNull(firstPost.getBody());
    }

    @Test
    public void testGetPostById() {
        // Act
        Response response = given()
                .spec(getRequestSpecification())
                .pathParam("id", 1)
                .when()
                .get("/posts/{id}");

        // Assert
        assertEquals(200, response.getStatusCode());
        
        Post post = response.as(Post.class);
        assertEquals(Integer.valueOf(1), post.getId());
        assertNotNull(post.getTitle());
        assertNotNull(post.getBody());
    }
}
