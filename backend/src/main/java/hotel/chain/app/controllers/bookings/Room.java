package hotel.chain.app.controllers.bookings;

import java.util.ArrayList;
import java.util.Collections;

public class Room {
    public RoomType type;
    public String number;
    public int floor;
    public boolean isClean;
    public boolean isOccupied;
    public ArrayList<Booking> bookings;

    public Room(RoomType type, String number, int floor, boolean isClean, boolean isOccupied, ArrayList<Booking> bookings){
        this.type = type;
        this.number = number;
        this.floor = floor;
        this.isClean = isClean;
        this.isOccupied = isOccupied;
        Collections.copy(this.bookings, bookings);
    }
}
