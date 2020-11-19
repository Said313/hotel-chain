package hotel.chain.app.controllers.authorization;

import hotel.chain.app.constants.authorization.GuestCategories;
import hotel.chain.app.constants.authorization.Id_type;
import hotel.chain.app.constants.authorization.UserType;
import hotel.chain.app.roles.Guest;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class SignupRequest {
    private String request;

    private String firstname;
    private String lastname;
    private String login;
    private String password;
    private Id_type id_type;
    private String id_number;
    private String address;
    private String mobile_phone;
    private String home_phone;
    private UserType userType;
    private GuestCategories guestCategory;
    private int hotelId;

    public String getFirstname() {return firstname;}
    public String getLastname() {return lastname;}
    public String getLogin() {return login;}
    public String getPassword() {return password;}
    public Id_type getId_type() {return id_type;}
    public String getId_number() {return id_number;}
    public String getAddress() {return address;}
    public String getMobile_phone() {return mobile_phone;}
    public String getHome_phone() {return home_phone;}
    public GuestCategories getGuestCategory() {return guestCategory; }
    public UserType getUserType() {return  userType; }
    public int getHotelId() {return  hotelId; }


    public SignupRequest(String request){
        this.request = request;
        parse();
    }

    private void parse(){

        try {
            JSONObject json = new JSONObject(request);

            firstname = json.getString("firstname");
            lastname = json.getString("lastname");
            login = json.getString("login");
            password = json.getString("password");
            id_number = json.getString("id_number");
            address = json.getString("address");
            mobile_phone = json.getString("mobile_phone");
            home_phone = json.getString("home_phone");
            hotelId = json.getInt("hotel_id");
            id_type = Id_type.getByName(json.getString("id_type"));
            userType = UserType.getByName(json.getString("user_type"));

            if (userType == UserType.GUEST){
                guestCategory = GuestCategories.getByName(json.getString("category"));
            } else {
                guestCategory = GuestCategories.EMPTY;
            }

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

}