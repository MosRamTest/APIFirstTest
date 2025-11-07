package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    @Test
    void loginWithHardcodedCredentials() {
        // Accept self-signed or invalid TLS for testing (remove in production)
        RestAssured.useRelaxedHTTPSValidation();

        String payload = """
                {
                  "email": "king@gmail.com",
                  "password": "King1234"
                }
                """;

        String url = "https://www.ndosiautomation.co.za/API/login";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(payload)
            .when()
                .post(url)
            .then()
                .log().ifValidationFails()
                .extract().response();

        System.out.println("Login response status: " + response.getStatusCode());
        System.out.println("Login response body: " + response.asString());

        // Basic assertion: expect HTTP 200 OK
        assertEquals(200, response.getStatusCode(), "Expected HTTP 200 from login endpoint");
    }
}

