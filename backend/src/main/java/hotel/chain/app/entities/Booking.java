package hotel.chain.app.entities;

import hotel.chain.app.constants.bookings.BookingState;
import hotel.chain.app.controllers.bookings.BookingCreateRequestParser;

import java.util.ArrayList;
import java.util.Date;

public class Booking {
    public int id;
    public int guestId;
    public Season during;
    public Date checkIn;
    public Date checkout;
    public float bill;
    public ArrayList<AdditionalService> additionalServices;

    public Booking(int id, int guestId, Season during, Date checkIn, Date checkout,
                   float bill, ArrayList<AdditionalService> additionalServices){
        this.id = id;
        this.guestId = guestId;
        this.during = during;
        this.checkIn = checkIn;
        this.checkout = checkout;
        this.bill = bill;
        this.additionalServices = new ArrayList<>(additionalServices);
    }

    public Booking(int guestId, Season during, Date checkIn, Date checkout,
                   float bill, ArrayList<AdditionalService> additionalServices){
        this.guestId = guestId;
        this.during = during;
        this.checkIn = checkIn;
        this.checkout = checkout;
        this.bill = bill;
        this.additionalServices = new ArrayList<>(additionalServices);
    }

    public Booking() {
        id = 0;
        guestId = 0;
        during = new Season();
        checkIn = new Date();
        checkout = new Date();
        bill = 0;
        additionalServices = new ArrayList<>();
    }


    @Override
    public String toString(){

        return "id: " + id + ", guestId: " + guestId + ", during: " + during + ", checkIn: " + checkIn + ", checkOut: " +
                checkout + " bill: " + bill;
    }
}
