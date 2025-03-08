package org.example.service;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNot.not;

@QuarkusTest
public class RepositoryServiceTest {

    @Test
    void fetchingRepositoriesWithBranches(){
        String username = "SebastianGalan76";

        RestAssured.given()
                .when()
                .get("/repos/" + username)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", not(empty()))
                .body("[0].name", notNullValue())
                .body("[0].ownerLogin", equalTo(username))
                .body("[0].branches", not(empty()))
                .body("[0].branches[0].name", notNullValue())
                .body("[0].branches[0].lastCommitSha", notNullValue());
    }
}
