package hotel.chain.app;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class SignupRequest {
    private String request;

    public SignupRequest(String request){
        this.request = request;
    }

    public User parseRequest() throws JSONException {
        JSONObject json = new JSONObject(request);

        return new User(
            json.getString("firstname"),
            json.getString("lastname"),
            json.getString("password"),
            "id_type",
            json.getString("id_number"),
            json.getString("address"),
            json.getString("mobile_phone"),
            json.getString("home_phone")
        );
    }
}
