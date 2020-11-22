package hotel.chain.app.controllers.bookings;

import hotel.chain.app.constants.bookings.AdditionalServicesTableColumns;
import hotel.chain.app.entities.AdditionalService;
import hotel.chain.app.entities.Booking;
import hotel.chain.app.entities.Room;
import hotel.chain.app.entities.RoomType;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.math.BigDecimal;
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
    private ArrayList<AdditionalService> additionalServices;
    private int guestId;

    public BookingCreateRequestParser(String request){

        this.request = request;
        roomDemands = new ArrayList<>();
        additionalServices = new ArrayList<>();
        parse();
    }

    private void parse()
    {
        try
        {
            JSONObject json = new JSONObject(request);
            hotelName = json.getString("hotelName");
            guestId = json.getInt("guestId");
            JSONArray rds = json.getJSONArray("roomDemand");
            
            for (int i=0;i<rds.length();i++)
            {
                JSONObject roomDemandJSON = rds.getJSONObject(i);
                String roomTypeName = roomDemandJSON.getString("name");
                int number = roomDemandJSON.getInt("number");
                RoomDemand roomDemand = new RoomDemand(roomTypeName, number);
                roomDemands.add(roomDemand);
            }

            JSONArray ads = json.getJSONArray("additionalServices");
            for (int i=0;i<ads.length();i++)
            {
                JSONObject additionalServiceJSON = ads.getJSONObject(i);
                AdditionalService additionalService = new AdditionalService(
                        additionalServiceJSON.getInt("serviceID"),
                        additionalServiceJSON.getString("name"),
                        BigDecimal.valueOf(additionalServiceJSON.getDouble("price")).floatValue()
                );

                additionalServices.add(additionalService);
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
    public ArrayList<AdditionalService> getAdditionalServices() {return additionalServices;}
}
