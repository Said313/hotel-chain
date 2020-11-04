package hotel.chain.app.controllers.authorization;

import hotel.chain.app.constants.authorization.GuestCategories;
import hotel.chain.app.constants.authorization.Id_type;
import hotel.chain.app.roles.Guest;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class SignupRequest {
    private String request;

    public SignupRequest(String request){
        this.request = request;
    }

    public Guest parseRequest() throws JSONException {
        JSONObject json = new JSONObject(request);

        Id_type id_type;
        String id_type_str = json.getString("id_type");
        switch (id_type_str){
            case "us_passport": id_type = Id_type.US_PASSPORT;
                break;
            case "driving_license": id_type = Id_type.DRIVING_LICENSE;
                break;
            default: id_type = Id_type.NOT_PROVIDED;
        }

        GuestCategories gc;
        String category = json.getString("category");
        switch (category){
            case "military": gc = GuestCategories.MILITARY;
                break;
            case "government": gc = GuestCategories.GOVERNMENT;
                break;
            case "vip": gc = GuestCategories.VIP;
                break;
            default: gc  = GuestCategories.NONE;
        }

        return new Guest(
                json.getString("firstname"),
                json.getString("lastname"),
                json.getString("login"),
                json.getString("password"),
                id_type,
                json.getString("id_number"),
                json.getString("address"),
                json.getString("mobile_phone"),
                json.getString("home_phone"),
                gc
        );
    }

}