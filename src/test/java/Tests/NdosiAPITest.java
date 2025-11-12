package Tests;


import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.*;

import static Common.commonTestData.*;
import static RequestBuilder.NdosiAPIRequestBuilder.*;

@Test
@Feature("Ndosi API Tests")
@Story("Login")

public class NdosiAPITest {

    @Description("I want to login to Ndosi API with valid credentials")
    public void loginTest()
    {
        loginResponse().
                then().
                log().all().
                assertThat().statusCode(success_status_code);
    }


}
