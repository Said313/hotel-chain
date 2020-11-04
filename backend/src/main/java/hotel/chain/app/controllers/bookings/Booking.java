package hotel.chain.app.controllers.bookings;

import hotel.chain.app.constants.bookings.BookingState;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {
    public int guestId;
    public Season during;
    public Date checkIn;
    public Date checkout;
    public BookingState state;
    public float bill;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public Booking(int guestId, Season during, Date checkIn, Date checkout, BookingState state, float bill){
        this.guestId = guestId;
        this.during = during;
        this.checkIn = checkIn;
        this.checkout = checkout;
        this.state = state;
        this.bill = bill;
    }
}
