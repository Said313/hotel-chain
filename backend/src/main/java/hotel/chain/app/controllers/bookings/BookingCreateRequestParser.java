package hotel.chain.app.controllers.bookings;

import hotel.chain.app.entities.Booking;
import hotel.chain.app.entities.Room;
import hotel.chain.app.entities.RoomType;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

class RoomDemand{
    private String roomTypeName;
    private int number;

    public RoomDemand(String roomTypeName, int number){
        this.number = number;
        this.roomTypeName = roomTypeName;
    }
    @Override
    public String toString()
    {
        return "type: " + roomTypeName + ", number: " + number;
    }

    public String getRoomTypeName(){return roomTypeName; }
    public int getNumber(){return number; }

}
public class BookingCreateRequestParser {

    private String request;
    private String hotelName;
    private ArrayList<RoomDemand> roomDemands;
    private int guestId;

    public BookingCreateRequestParser(String request){

        this.request = request;
        roomDemands = new ArrayList<>();
    }

    public void parse()
    {
        try
        {
            JSONObject json = new JSONObject(request);
            hotelName = json.getString("hotelName");
            guestId = json.getInt("guestId");
            JSONArray arr = json.getJSONArray("roomDemand");
            for (int i=0;i<arr.length();i++)
            {
                JSONObject roomDemandJSON = arr.getJSONObject(i);
                String roomTypeName = roomDemandJSON.getString("name");
                int number = roomDemandJSON.getInt("number");
                RoomDemand roomDemand = new RoomDemand(roomTypeName, number);
                roomDemands.add(roomDemand);
            }

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<RoomDemand> getRoomDemands(){return roomDemands;}
    public String getHotelName(){ return hotelName; }
    public int getGuestId(){ return guestId; }
}
