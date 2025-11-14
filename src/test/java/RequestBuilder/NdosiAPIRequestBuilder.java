package RequestBuilder;

import io.restassured.response.Response;

import static Common.BasePaths.*;
import static PayloadBuilder.NdosiAPIPayloadBuilder.*;
import static PayloadBuilder.NdosiAPIPayloadBuilder.registerPayload;
import static io.restassured.RestAssured.*;

public class NdosiAPIRequestBuilder {

    public static Response loginResponse (String email, String password) {

        return given().
                baseUri(NdosiBaseUrl).
                basePath("/login").
                contentType("application/json").
                body(loginPayload(email,password)).
                log().all().
                post().
                then().
                log().all().
                extract().response();
    }
    //register API Request
    public static Response registerResponse(String firstName, String lastName,String email,String password, String confirmPassword) {
        return given().
                baseUri(NdosiBaseUrl).
                basePath("/register").
                contentType("application/json").
                body(registerPayload(firstName,lastName,email,password,confirmPassword)).
                log().all().
                post().
                then().
                log().all().
                extract().response();

        //System.out.println();
    }
}
