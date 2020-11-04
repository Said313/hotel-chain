package hotel.chain.app.controllers.bookings;

import java.util.ArrayList;
import java.util.Collections;

public class RoomType {
    public String name;
    public float size;
    public int capacity;
    public float fixedPrice;
    public Hotel hotel;
    public ArrayList<RoomTypeFeature> features;
    public ArrayList<Room> rooms;

    public RoomType(String name, float size, int capacity, float fixedPrice, Hotel hotel,
                    ArrayList<RoomTypeFeature> features, ArrayList<Room> rooms){

        this.name = name;
        this.size = size;
        this.capacity = capacity;
        this.fixedPrice = fixedPrice;
        this.hotel = hotel;
        Collections.copy(this.features, features);
        Collections.copy(this.rooms, rooms);
    }
}
