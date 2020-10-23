package hotel.chain.app;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

@Path("/items")
public class TestJaxRsService {

    public User user;

    public TestJaxRsService(){
        //user = new User("Olzhas", "Jalmukhambetov", "olzhas.jalmukhambetov", "1234","Passport",
          //      "0001", "Main Street, 12", "123456", "1234","None");
    }

    @GET
    public Response getUser() {
        return Response.ok(user).build();
    }
    
    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") String id) {
        String r = "JAX-RS: you requested item with id \"" + id + "\"";
        return Response.ok(r).build();
    }

    @POST
    public Response signUp(@FormParam("firstname") String firstname,
                            @FormParam("lastname") String lastname,
                            @FormParam("password") String password,
                            @FormParam("id_type") String id_type,
                            @FormParam("id_number") String id_number,
                            @FormParam("address") String address,
                            @FormParam("mobile_phone") String mobile_phone,
                            @FormParam("home_phone") String home_phone,
                            @FormParam("category") String category){

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

        } catch (ClassNotFoundException
                | InstantiationException
                | InvocationTargetException
                | IllegalAccessException
                | NoSuchMethodException
                | SQLException e) {

            e.printStackTrace();
        }








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


        return Response.ok().build();
    }
}
