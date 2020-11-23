package hotel.chain.app.entities;

import java.sql.Date;
import java.util.ArrayList;

public class Hotel {
    public int id;
    public String name;
    public String address;
    public String city;
    public String description;
    public ArrayList<RoomType> roomTypes;
    public ArrayList<Season> seasons;
    public ArrayList<AdditionalService> additionalServices;

    public Hotel(int id, String name, String address, String city, String description,
                 ArrayList<RoomType> roomTypes, ArrayList<Season> seasons,
                 ArrayList<AdditionalService> additionalServices){
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.description = description;
        this.roomTypes = new ArrayList<>(roomTypes);
        this.seasons = new ArrayList<>(seasons);
        this.additionalServices = new ArrayList<>(additionalServices);
    }


    public Hotel(int id, String name, String address, String city, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.description = description;
    }

    public Hotel(){
        id = 0;
        name = "empty";
        address = "empty";
        city = "empty";
        description = "empty";
        roomTypes = new ArrayList<>();
    }

    @Override
    public String toString()
    {
        return "id: " + id +
                ", name: " + name +
                ", address: " + address +
                ", city: " + city +
                ", description: " + description +
                ", roomTypes: " + roomTypes.toString();

    }

    public RoomType getRoomTypeByName(String roomTypeName)
    {
        RoomType res = new RoomType();
        for (RoomType roomType : roomTypes)
        {
            if (roomTypeName.equals(roomType.name))
            {
                res = roomType;
            }
        }

        return res;
    }

    public Season getSeason(Date start, Date end) {

        Season res = new Season();
        System.out.println(start);

        for (Season season : seasons){
            System.out.println(season);
            if (season.starts.before(start) && season.ends.after(start) || season.starts.equals(start) || season.ends.equals(start)){
                res = season;
            }
        }
        return res;
    }
}
