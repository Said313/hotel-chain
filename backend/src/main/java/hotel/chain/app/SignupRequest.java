package hotel.chain.app;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class SignupRequest {
<<<<<<< HEAD
	public String firstname;
	public String lastname;
	public String password;
	public String repeat_password;
	public String id_type;
	public String id_number;
	public String address;
	public String mobile_phone;
	public String home_phone;
	public String category;
=======
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
>>>>>>> 62ffea518c603d7384ebfb39fb95133ad12c6083
}
