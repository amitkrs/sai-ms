package com.techie.microservices.product;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.testcontainers.containers.MongoDBContainer;

public class ProductServiceApplicationTest extends AbstractIT {

    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

    static {
        mongoDBContainer.start();
    }

    private static final String reqBody = """
                {
                    "name" : "iphone15",
                    "code" : "mobile_apple_i15",
                    "price" : 1500.15,
                    "quantity" : 150
                }
                """;

    private static final String invalidReqBody = """
                {
                    "name" : "iphone15",
                    "code" : "mobile_apple_i15",
                    "quantity" : 150
                }
                """;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void contextLoads() {}

    @Test
    public void givenValidProductRequest_whenCreateProduct_shouldReturnSavedProduct() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(reqBody)
                .when()
                .post("/api/products")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("data", Matchers.notNullValue())
                .body("data", Matchers.hasEntry("name", "iphone15"));
    }

    @Test
    public void givenInvalidProductRequest_whenCreateProduct_shouldReturnBadRequest() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(invalidReqBody)
                .when()
                .post("/api/products")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", Matchers.notNullValue());
    }

}

