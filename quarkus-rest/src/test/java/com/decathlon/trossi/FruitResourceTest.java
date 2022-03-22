package com.decathlon.trossi;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;

@QuarkusTest
public class FruitResourceTest {
    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/fruits")
          .then()
             .statusCode(200);
    }
    @Test
    public void testHelloEndpoint2() {
        given()
          .when().get("/fruits")
          .then()
             .statusCode(200);
    }
    @Test
    public void testHelloEndpoint3() {
        given()
          .when().get("/fruits")
          .then()
             .statusCode(200);
    }
    @Test
    public void testHelloEndpoint4() {
        given()
          .when().get("/fruits")
          .then()
             .statusCode(200);
    }
    @Test
    public void testHelloEndpoint5() {
        given()
          .when().get("/fruits")
          .then()
             .statusCode(200);
    }
    @Test
    public void testHelloEndpoint6() {
        given()
          .when().get("/fruits")
          .then()
             .statusCode(200);
    }
}
