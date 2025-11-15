package Tests;


import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.*;

import static Common.commonTestData.*;
import static RequestBuilder.NdosiAPIRequestBuilder.*;
import static Utils.generateTestData.*;
import static org.hamcrest.Matchers.*;

@Test
@Feature("Ndosi API Tests")
@Story("Login")



public class NdosiAPITest {

    @Description("I want to Register User to Ndosi API")
    public void registerTest() {

        registerResponse(firstName, lastName, email, password, password).
                then().
                log().all().
                assertThat().
                statusCode(created_status_code).body(containsString("data")).
                body("success", equalTo(true)).
                body("message", equalTo("User registered successfully")).
                body("data.email", equalTo(email)).
                body("data.firstName", equalTo(firstName)).
                body("data.lastName", equalTo(lastName)).
                body("data.id", notNullValue()).
                body("data.createdAt", notNullValue()); ;

    }

    @Description("I want to login to Ndosi API with valid credentials")
    @Test(dependsOnMethods = "registerTest")
    public void loginTest()
    {
        loginResponse(email, password).
                then().
                log().all().
                assertThat().statusCode(success_status_code).
                body("success", equalTo(true)).
                body("message", equalTo("Login successful")).
                body("data.token", notNullValue()).
                body("data.user.id", notNullValue()).
                body("data.user.firstName", equalTo(firstName)).
                body("data.user.lastName", equalTo(lastName)).
                body("data.user.email", equalTo(email)).
                body("data.user.createdAt", notNullValue()).
                body("data.user.updatedAt", notNullValue());
    }


}
