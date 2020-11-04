package hotel.chain.app.controllers.bookings;

import java.util.ArrayList;
import java.util.Collections;

public class Hotel {
    public String name;
    public String address;
    public String city;
    public String description;
    public ArrayList<RoomType> roomTypes;

    public Hotel(String name, String address, String city, String description, ArrayList<RoomType> roomTypes){
        this.name = name;
        this.address = address;
        this.city = city;
        this.description = description;
        Collections.copy(this.roomTypes, roomTypes);
    }
}
