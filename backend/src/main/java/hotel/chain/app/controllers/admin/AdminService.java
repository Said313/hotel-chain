package hotel.chain.app.controllers.admin;

import com.google.gson.Gson;
import hotel.chain.app.controllers.profile.ProfileEditRequest;
import hotel.chain.app.database.AdminDBHandler;
import hotel.chain.app.database.ProfileDBHandler;
import hotel.chain.app.entities.Booking;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/admin")
public class AdminService {

    @POST
    @Path("/bookings")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBookings(String request) {

        AdminDBHandler db = new AdminDBHandler();
        ArrayList<Booking> bookings = new ArrayList<>();
        bookings = db.getAllBookings();
        return Response.ok(new Gson().toJson(bookings)).build();
    }

}
