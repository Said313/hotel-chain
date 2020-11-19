package hotel.chain.app.controllers.authorization;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import hotel.chain.app.constants.authorization.Id_type;
import hotel.chain.app.constants.authorization.UserType;
import hotel.chain.app.database.AuthDBHandler;
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

        AuthDBHandler dbh = new AuthDBHandler();
        User user = dbh.getUserByLogin(login);
        dbh.closeConnection();

        if (password.equals(user.password)) {
            return Response.ok(user).build();
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
        AuthDBHandler db = new AuthDBHandler();
        db.signUp(sur);
        db.closeConnection();

        return Response.ok().build();
    }

    @POST
    @Path("/checkLogin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkLogin(String request){

        String login = new LoginRequest(request).getLogin();
        AuthDBHandler db = new AuthDBHandler();
        User user = db.getUserByLogin(login);
        db.closeConnection();

        boolean userExists = (user.type != UserType.EMPTY);

        Gson gson = new Gson();
        String json = gson.toJson(userExists);
        return Response.ok(json).build();
    }

}