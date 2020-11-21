package hotel.chain.app.controllers.profile;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class ProfileCancelBookingRequest {
    private String request;
    private int bookingID;

    public int getBookingID() {
        return bookingID;
    }

    public ProfileCancelBookingRequest(String request) {
        this.request = request; parse();
    }

    private void parse(){
        try {
            JSONObject json = new JSONObject(request);
            bookingID = json.getInt("bookingID");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
