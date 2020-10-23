package hotel.chain.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("/services")
public class HotelChainApp extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> empty = new HashSet<Class<?>>();

    public HotelChainApp() {

        try{
            String url = "jdbc:mysql://localhost/mydb?serverTimezone=Asia/Almaty";
            String user = "root";
            String password = "IronDragon1327";
            Connection conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e){
            e.printStackTrace();
        }
        /*try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connection successful!");
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }*/

        singletons.add(new AuthorizationService());
    }

    @Override
    public Set<Class<?>> getClasses() {
        return empty;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
