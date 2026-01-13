package com.jsonplaceholder.tests;

import com.jsonplaceholder.config.BaseTest;
import com.jsonplaceholder.models.User;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

/**
 * Testes para o endpoint GET /users
 */
public class UsersTest extends BaseTest {

    @Test
    public void testGetAllUsers() {
        // Act
        Response response = given()
                .spec(getRequestSpecification())
                .when()
                .get("/users");

        // Assert
        assertEquals(200, response.getStatusCode());
        
        List<User> users = response.jsonPath().getList(".", User.class);
        assertTrue(users.size() > 0);
        
        // Validar primeiro usuário
        User firstUser = users.get(0);
        assertNotNull(firstUser.getId());
        assertNotNull(firstUser.getName());
        assertNotNull(firstUser.getEmail());
    }
}
