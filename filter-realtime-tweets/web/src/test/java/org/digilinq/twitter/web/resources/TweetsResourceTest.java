package org.digilinq.twitter.web.resources;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;

@QuarkusTest
class TweetsResourceTest {

    @Test
    void shouldReturnAllBooks() {
        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .get("/api/v1/tweets")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

}