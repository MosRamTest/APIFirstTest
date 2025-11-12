package PayloadBuilder;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.simple.JSONObject;
import org.junit.Test;

public class NdosiAPIPayloadBuilder {


    public static JSONObject loginPayload ()
    {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("email","king@gmail.com");
        jsonObject.put("password","King1234");

        return jsonObject;
    }
}
