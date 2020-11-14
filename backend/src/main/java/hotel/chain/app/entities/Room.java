package hotel.chain.app.entities;

import hotel.chain.app.entities.Booking;

import java.util.ArrayList;
import java.util.Collections;

public class Room {
    public int id;
    public String number;
    public int floor;
    public boolean isClean;
    public boolean isOccupied;
    public ArrayList<Booking> bookings;

    public Room(int id, String number, int floor, boolean isClean, boolean isOccupied, ArrayList<Booking> bookings) {
        this.id = id;
        this.number = number;
        this.floor = floor;
        this.isClean = isClean;
        this.isOccupied = isOccupied;
        this.bookings = new ArrayList<>(bookings);
    }
}
