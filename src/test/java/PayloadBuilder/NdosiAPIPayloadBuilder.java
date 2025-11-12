package PayloadBuilder;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.simple.JSONObject;
import org.junit.Test;

public class NdosiAPIPayloadBuilder {


    public static JSONObject loginPayload (String email, String password)
    {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("email",email);
        jsonObject.put("password",password);

        return jsonObject;
    }
}
