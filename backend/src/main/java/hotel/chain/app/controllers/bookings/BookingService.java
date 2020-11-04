package hotel.chain.app.controllers.bookings;

import hotel.chain.app.DBHandler;
import hotel.chain.app.constants.bookings.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/booking")
public class BookingService {

    private ArrayList<Hotel> hotels;
    private ArrayList<RoomType> roomTypes;
    private ArrayList<Room> rooms;
    private ArrayList<Booking> bookings;
    private ArrayList<Season> seasons;
    private boolean isInitialized;

    public BookingService(){
         hotels = new ArrayList<>();
         roomTypes = new ArrayList<>();
         rooms = new ArrayList<>();
         bookings = new ArrayList<>();
         seasons = new ArrayList<>();
         isInitialized = false;
        System.out.println("constructor");
    }

    @POST
    @Path("/query")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvailableRooms(){

        System.out.println("method");
        if (!isInitialized){
            populateArrays();
            System.out.println("true");
        }


        return Response.ok().build();
    }

    private void populateArrays(){
        System.out.println("populateArrays");
        DBHandler dbh = new DBHandler();


        String selectSeasons = "SELECT * FROM " + SeasonsTableColumns.TABLE_NAME;
        String selectBookings = "SELECT * FROM" + BookingsTableColumns.TABLE_NAME;
        String selectRooms = "SELECT * FROM " + RoomsTableColumns.TABLE_NAME;
        String selectRoomTypes = "SELECT * FROM" + RoomTypesTableColumns.TABLE_NAME;
        String selectHotels = "SELECT * FROM " + HotelsTableColumns.TABLE_NAME;

        ResultSet rsSeasons = dbh.executeSelect(selectSeasons);
        ResultSet rsBookings = dbh.executeSelect(selectBookings);
        ResultSet rsRooms = dbh.executeSelect(selectRooms);
        ResultSet rsRoomTypes = dbh.executeSelect(selectRoomTypes);
        ResultSet rsHotels = dbh.executeSelect(selectHotels);

        try{
            while(rsSeasons.next()){

                int id = rsSeasons.getInt(SeasonsTableColumns.ID);
                String season_name = rsSeasons.getString(SeasonsTableColumns.SEASON_NAME);
                float price_factor = rsSeasons.getFloat(SeasonsTableColumns.PRICE_FACTOR);
                Date starts = rsSeasons.getDate(SeasonsTableColumns.STARTS);
                Date ends = rsSeasons.getDate(SeasonsTableColumns.ENDS);

                Season season = new Season(id, season_name, price_factor, starts, ends);
                System.out.println(season.season_name);
                seasons.add(season);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
