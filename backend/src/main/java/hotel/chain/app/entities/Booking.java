package hotel.chain.app.entities;

import hotel.chain.app.constants.bookings.BookingState;

import java.util.Date;

public class Booking {
    public int id;
    public int guestId;
    public Season during;
    public Date checkIn;
    public Date checkout;
    public float bill;

    public Booking(int id, int guestId, Season during, Date checkIn, Date checkout, float bill){
        this.id = id;
        this.guestId = guestId;
        this.during = during;
        this.checkIn = checkIn;
        this.checkout = checkout;
        this.bill = bill;
    }

    public Booking(int guestId, Season during, Date checkIn, Date checkout, float bill){
        this.guestId = guestId;
        this.during = during;
        this.checkIn = checkIn;
        this.checkout = checkout;
        this.bill = bill;
    }

    @Override
    public String toString(){

        return "id: " + id + ", guestId: " + guestId + ", during: " + during + ", checkIn: " + checkIn + ", checkOut: " +
                checkout + " bill: " + bill;
    }
}
