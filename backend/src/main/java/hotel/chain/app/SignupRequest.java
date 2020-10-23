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

        Id_type doc_type = Id_type.NOT_PROVIDED;
        /*String id_type = json.getString("id_type");
        switch (id_type){
            case "us_passport": doc_type = Id_type.US_PASSPORT;
            case "driving_license": doc_type = Id_type.DRIVING_LICENSE;
        }*/


        return new User(
            json.getString("firstname"),
            json.getString("lastname"),
            json.getString("password"),
            doc_type,
            json.getString("id_number"),
            json.getString("address"),
            json.getString("mobile_phone"),
            json.getString("home_phone")
        );
    }
}
