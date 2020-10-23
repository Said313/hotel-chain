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
<<<<<<< HEAD
	    
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
	        	this.list.add(guest);
	        	return Response.ok(json).build();
	        }
	        
	        else {
	        	json = gson.toJson("invalid login or password", String.class);
	        	return Response.ok(json).build();
	        }
    }
=======
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
>>>>>>> 62ffea518c603d7384ebfb39fb95133ad12c6083

    //Complete Post public signUp method
    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
<<<<<<< HEAD
    public Response signUp(SignupRequest request){
        String firstname = request.firstname;
        String lastname = request.lastname;
        String password = request.password;
        String id_type = request.id_type;
        String id_number = request.id_number;
        String address = request.address;
        String mobile_phone = request.mobile_phone;
        String home_phone = request.home_phone;
        String category = request.category;

        User guest;
        Gson gson = new Gson();

        String createTable = "INSERT INTO products ("
                + "Id INT PRIMARY KEY AUTO_INCREMENT, "
                + "ProductName VARCHAR(20), "
                + "Price INT)";

        System.out.println(firstname);
        String registerUser = "INSERT guests(firstname, lastname, login, password, idtype, idnumber, address, mobilephone, homephone) "
                            + "Values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            DBHandler dbh = new DBHandler();
            PreparedStatement ps = dbh.getDbConnection().prepareStatement(registerUser);
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, "login");
            ps.setString(4, "password");
            ps.setString(5, id_type);
            ps.setString(6, id_number);
            ps.setString(7, address);
            ps.setString(8, mobile_phone);
            ps.setString(9, home_phone);


            ps.executeUpdate();

=======
    //public Response signup(SignupRequest request){
	public Response signup(String request){
        SignupRequest sur = new SignupRequest(request);

        try{
            User user = sur.parseRequest();
            DBHandler dbh = new DBHandler();
            dbh.signUpUser(user);
>>>>>>> 62ffea518c603d7384ebfb39fb95133ad12c6083
        } catch (ClassNotFoundException
                | InstantiationException
                | InvocationTargetException
                | IllegalAccessException
                | NoSuchMethodException
<<<<<<< HEAD
                | SQLException e) {
=======
                | SQLException
                | JSONException e) {
>>>>>>> 62ffea518c603d7384ebfb39fb95133ad12c6083

            e.printStackTrace();
        }


<<<<<<< HEAD






        /*String login = firstname + "." + lastname;

        user = new User(firstname, lastname, login, password, id_type,
                id_number, address, mobile_phone, home_phone, category);

        DBHandler dbh = new DBHandler();

        dbh.signUpUser(firstname, lastname, login, password, id_type,
                id_number, address, mobile_phone, home_phone, category);
        //dbh.signUpUser(user);*/


        /*try{
            String url = "jdbc:mysql://localhost/hotel?serverTimezone=Asia/Almaty";
            String username = "root";
            String password2 = "IronDragon1327";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password2)){

                System.out.println("Connection to Store DB succesfull!");
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }*/

        json = gson.toJson(guest, User.class);
        return Response.ok(json).build();
=======
        return Response.ok().build();
>>>>>>> 62ffea518c603d7384ebfb39fb95133ad12c6083
    }
}
