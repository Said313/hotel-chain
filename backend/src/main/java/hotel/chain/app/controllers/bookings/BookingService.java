package hotel.chain.app.controllers.bookings;

import com.google.gson.Gson;
import hotel.chain.app.constants.bookings.BookingState;
import hotel.chain.app.database.BookingDBHandler;
import hotel.chain.app.entities.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.ArrayList;

@Path("/booking")
public class BookingService {

    private ArrayList<Hotel> hotels;
    private String destination;
    private Date start;
    private Date end;

    public BookingService(){
        hotels = new ArrayList<>();
    }

    @POST
    @Path("/create")
    public Response createBooking(String request)
    {
        BookingCreateRequestParser parser = new BookingCreateRequestParser(request);
        String hotelName = parser.getHotelName();
        ArrayList<RoomDemand> roomDemands = parser.getRoomDemands();
        int guestId = parser.getGuestId();

        ArrayList<Room> bookedRooms = new ArrayList<>();
        Hotel hotel = getHotelByName(hotelName);
        for (RoomDemand roomDemand : roomDemands)
        {
            RoomType roomType = hotel.getRoomTypeByName(roomDemand.getRoomTypeName());
            for (int i=1;i<=roomDemand.getNumber();i++)
            {
                Room room = roomType.rooms.remove(0);
                bookedRooms.add(room);

                Season during = hotel.getCurrentSeason();
                float bill = issueBill();
                Booking booking = new Booking(guestId, during, start, end, bill);
                BookingDBHandler db = new BookingDBHandler();
                db.createBooking(room.id, booking);
                db.closeConnection();
            }
        }



        Gson gson = new Gson();
        return Response.ok(gson.toJson(bookedRooms)).build();
    }

    private float issueBill() {
        return 0;
    }

    @POST
    @Path("/query")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvailableHotels(String request)
    {
        BookingQueryParser br = new BookingQueryParser(request);
        destination = br.getDestination();
        start = br.getStart();
        end = br.getEnd();

        BookingDBHandler bdbh = new BookingDBHandler();

        hotels = bdbh.getAvailableHotels(destination, start, end);
        Gson gson = new Gson();
        return Response.ok(gson.toJson(hotels)).build();
    }

    public Hotel getHotelByName(String name)
    {
        Hotel res = new Hotel();
        for (Hotel hotel : hotels)
        {
            if (name.equals(hotel.name))
            {
                res = hotel;
            }
        }

        return res;
    }


}