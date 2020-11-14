package hotel.chain.app.controllers.bookings;

import com.google.gson.Gson;
import hotel.chain.app.constants.bookings.BookingState;
import hotel.chain.app.constants.bookings.HotelsTableColumns;
import hotel.chain.app.database.AuthDBHandler;
import hotel.chain.app.database.BookingDBHandler;
import hotel.chain.app.entities.Booking;
import hotel.chain.app.entities.Hotel;
import hotel.chain.app.entities.Room;
import hotel.chain.app.entities.RoomType;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Path("/booking")
public class BookingService {

    private ArrayList<Hotel> hotels;

    public BookingService(){
        hotels = new ArrayList<>();
    }

    @POST
    @Path("/query")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvailableHotels(String request)
    {
        BookingRequest br = new BookingRequest(request);
        br.parse();

        BookingDBHandler bdbh = new BookingDBHandler();

        hotels = bdbh.getAvailableHotels(br);
        Gson gson = new Gson();
        return Response.ok(gson.toJson(hotels)).build();
    }


    private ArrayList<Hotel> filterByDate(ArrayList<Hotel> hotels, Date start, Date end)
    {
        ArrayList<Hotel> hotels_copy = new ArrayList<>(hotels);

        for (Hotel hotel : hotels_copy)
        {
            for (RoomType roomType : hotel.roomTypes)
            {
                for (Room room : roomType.rooms)
                {
                    for (Booking booking : room.bookings)
                    {
                        if (booking.state == BookingState.CURRENT)
                        {

                        }
                        else if (booking.state == BookingState.COMING)
                        {

                        }
                    }
                }
            }
        }

        return hotels_copy;
    }
}
