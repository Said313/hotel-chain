package hotel.chain.app.controllers.admin;

import com.google.gson.Gson;
import hotel.chain.app.controllers.profile.ProfileEditRequest;
import hotel.chain.app.controllers.profile.ScheduleGetRequest;
import hotel.chain.app.database.AdminDBHandler;
import hotel.chain.app.database.ProfileDBHandler;
import hotel.chain.app.entities.Booking;
import hotel.chain.app.entities.Room;
import hotel.chain.app.roles.Employee;
import hotel.chain.app.roles.User;

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

    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(String request) {

        AdminDBHandler db = new AdminDBHandler();
        ArrayList<User> users = new ArrayList<>();
        users = db.getAllUsers();
        return Response.ok(new Gson().toJson(users)).build();
    }

    @POST
    @Path("/employees")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees(String request) {

        AdminDBHandler db = new AdminDBHandler();
        ArrayList<Employee> employees = new ArrayList<>();
        employees = db.getAllEmployees();
        db.closeConnection();
        return Response.ok(new Gson().toJson(employees)).build();
    }

    @POST
    @Path("/getEmployee")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployee(String request) {

        AdminDBHandler db = new AdminDBHandler();
        ScheduleGetRequest parser = new ScheduleGetRequest(request);
        Employee employee = db.getEmployee(parser);
        db.closeConnection();
        return Response.ok(new Gson().toJson(employee)).build();
    }




}
