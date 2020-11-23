package hotel.chain.app.entities;

import java.util.ArrayList;

public class Receipt {
    private Booking booking;
    private RoomType roomType;

    public Receipt(Booking booking, RoomType roomType){
        this.booking = booking;
        this.roomType = roomType;
    }

    public Receipt() {
        booking = new Booking();
        roomType = new RoomType();
    }
}
