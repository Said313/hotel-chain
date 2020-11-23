package hotel.chain.app.controllers.bookings;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class CheckInOutRequest {
    private String request;
    private int bookingID;

    public CheckInOutRequest(String request){
        this.request = request; parse();
    }

    private void parse(){
        try {
            JSONObject json = new JSONObject(request);
            bookingID = json.getInt("bookingID");
        } catch (JSONException e ) {
            e.printStackTrace();
        }
    }

    public int getBookingID() {
        return bookingID;
    }
}
