package hotel.chain.app.controllers.profile;

import java.sql.Date;

public class BookingModificationRequest {
    private String request;
    private int bookingID;
    private int roomID;
    private Date checkIn;
    private Date checkOut;

    public BookingModificationRequest(String request){
        this.request = request; parse();
    }

    private void parse(){

    }
}
