package hotel.chain.app.entities;

import java.util.ArrayList;
import java.util.Collections;

public class RoomType {
    public int id;
    public String name;
    public float size;
    public int capacity;
    public float fixedPrice;
    public ArrayList<RoomTypeFeature> features;
    public ArrayList<Room> rooms;

    public RoomType(int id, String name, float size, int capacity, float fixedPrice,
                    //ArrayList<RoomTypeFeature> features,
                    ArrayList<Room> rooms){

        this.id = id;
        this.name = name;
        this.size = size;
        this.capacity = capacity;
        this.fixedPrice = fixedPrice;
        //this.features = new ArrayList<>(features);
        this.rooms = new ArrayList<>(rooms);
    }

}
