package hotel.chain.app;

import hotel.chain.app.constants.GuestCategories;
import hotel.chain.app.constants.Id_type;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class SignupRequest {
    private String request;

    public SignupRequest(String request){
        this.request = request;
    }

    public Guest parseRequest() throws JSONException {
        JSONObject json = new JSONObject(request);

        Id_type doc_type = Id_type.NOT_PROVIDED;
        /*String id_type = json.getString("id_type");
        switch (id_type){
            case "us_passport": doc_type = Id_type.US_PASSPORT;
            case "driving_license": doc_type = Id_type.DRIVING_LICENSE;
        }*/

        GuestCategories gc = GuestCategories.NONE;
        /*String category = json.getString("category");
        switch (category){
            case "military": gc = GuestCategories.MILITARY;
            case "government": gc = GuestCategories.GOVERNMENT;
            case "vip": gc = GuestCategories.VIP;
        }*/

        return new Guest(
                json.getString("firstname"),
                json.getString("lastname"),
                json.getString("login"),
                json.getString("password"),
                doc_type,
                json.getString("id_number"),
                json.getString("address"),
                json.getString("mobile_phone"),
                json.getString("home_phone"),
                gc
        );
    }

    public String parseLogin() throws JSONException {
        JSONObject json = new JSONObject(request);
        return json.getString("login");
    }
}