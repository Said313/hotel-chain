package hotel.chain.app;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/auth")
public class AuthorizationService {
    private List<User> list = new CopyOnWriteArrayList<User>();

    public AuthorizationService(){}

    /*//Complete Post public login method
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest request){

		String login = request.getLogin();
		String password = request.getPassword();

		Vector<String> vec = new Vector<String>();
		Guest guest;
        Gson gson = new Gson();
		String json;
		try {

			DBHandler dbh = new DBHandler();
			Statement ps = dbh.getDbConnection().createStatement();

			ResultSet rs=ps.executeQuery("select * from GuestsTableColumns.TABLE_NAME");
			try {
				while (rs.next()) {
					if(login.equals(rs.getString("login")) && password.equals(rs.getString("password"))) {

						vec.add(rs.getString("firstname"));
						vec.add(rs.getString("lastname"));
						vec.add(rs.getString("login"));
						vec.add(rs.getString("password"));
						vec.add(rs.getString("idtype"));
						vec.add(rs.getString("idnumber"));
						vec.add(rs.getString("address"));
						vec.add(rs.getString("mobilephone"));
						vec.add(rs.getString("homephone"));
						vec.add(rs.getString("category"));

						guest = new Guest(vec.get(0), vec.get(1), vec.get(2), vec.get(3), vec.get(4), vec.get(5), vec.get(6), vec.get(7), vec.get(8), vec.get(9));
					}

				}
			}finally {
				rs.close();
			}

		} catch (ClassNotFoundException
				| InstantiationException
				| InvocationTargetException
				| IllegalAccessException
				| NoSuchMethodException
				| SQLException e) {

			e.printStackTrace();
		}

		if(vec.size()>0) {
			json = gson.toJson(guest, Guest.class);
			return Response.ok(json).build();
		}

		else {
			json = gson.toJson("invalid login or password", String.class);
			return Response.ok(json).build();
		}
    }*/

    //Complete Post public signUp method
    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //public Response signup(SignupRequest request){
	public Response signup(String request){
        SignupRequest sur = new SignupRequest(request);

        try{
            User user = sur.parseRequest();
            DBHandler dbh = new DBHandler();
            dbh.signUpUser(user);
        } catch (ClassNotFoundException
                | InstantiationException
                | InvocationTargetException
                | IllegalAccessException
                | NoSuchMethodException
                | SQLException
                | JSONException e) {

            e.printStackTrace();
        }


        return Response.ok().build();
    }
}
