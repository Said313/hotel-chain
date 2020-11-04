package hotel.chain.app.controllers.authorization;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import hotel.chain.app.DBHandler;
import hotel.chain.app.roles.Guest;
import hotel.chain.app.roles.User;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/auth")
public class AuthorizationService {
    private List<User> list = new CopyOnWriteArrayList<User>();

    public AuthorizationService(){}

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(String request){

        LoginRequest lir = new LoginRequest(request);
        String login = lir.getLogin();
        String password = lir.getPassword();

        DBHandler dbh = new DBHandler();
        Guest guest = dbh.findGuestByLogin(login);

        if (password.equals(guest.password)) {
            return Response.ok(guest).build();
        } else {
            return Response.ok().build();
        }
    }


    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signup(String request){

        SignupRequest sur = new SignupRequest(request);
        DBHandler dbh = new DBHandler();
        Guest guest = null;

        try{
            guest = sur.parseRequest();
        } catch (JSONException e){
            e.printStackTrace();
        }

        dbh.signUpGuest(guest);

        return Response.ok().build();
    }

    @POST
    @Path("/checkLogin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkLogin(String request){

        User user = null;
        boolean userExists = false;
        String login = null;

        try {
            login = parseLogin(request);
        } catch (JSONException e){
            e.printStackTrace();
        }

        DBHandler dbh = new DBHandler();
        user = dbh.findUserByLogin(login);


        if (user != null){
            userExists = true;
        }

        Gson gson = new Gson();
        String json = gson.toJson(userExists);
        return Response.ok(json).build();
    }

    public String parseLogin(String request) throws JSONException {
        JSONObject json = new JSONObject(request);
        return json.getString("login");
    }
}