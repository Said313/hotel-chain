package hotel.chain.app.controllers.profile;

import hotel.chain.app.constants.bookings.BookingState;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class ProfileGetBookingsRequest {
    private String request;
    private int guestId;
    private BookingState bookingState;

    public ProfileGetBookingsRequest(String request){
        this.request = request; parse();
    }

    private void parse(){
        try {
            JSONObject json = new JSONObject(request);
            guestId = json.getInt("id");
            bookingState = BookingState.getByName(json.getString("bookingState"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getGuestId() { return guestId; }
    public BookingState getBookingState() { return  bookingState; }

}
