package hotel.chain.app;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHandler extends DBConfigs{
    private Connection dbConnection;

    public DBHandler() throws ClassNotFoundException, SQLException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {

        //String url = "jdbc:mysql://localhost/hotel?serverTimezone=Asia/Almaty";
        String url = "jdbc:mysql://" + dbHost + "/" + dbName + "?serverTimezone=Asia/Almaty";
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

        dbConnection = DriverManager.getConnection(url, dbLogin, dbPassword);
    }

    public Connection getDbConnection(){
        return dbConnection;
    }

    public void signUpUser(User user){

        String insert = "INSERT INTO " + GuestsTableColumns.TABLE_NAME + "("
                + GuestsTableColumns.FIRSTNAME + ","
                + GuestsTableColumns.LASTNAME + ","
                + GuestsTableColumns.LOGIN + ","
                + GuestsTableColumns.PASSWORD + ","
                + GuestsTableColumns.IDTYPE + ","
                + GuestsTableColumns.IDNUMBER + ","
                + GuestsTableColumns.ADDRESS + ","
                + GuestsTableColumns.MOBILEPHONE + ","
                + GuestsTableColumns.HOMEPHONE + ","
                + GuestsTableColumns.CATEGORY + ")"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        try
        {
            PreparedStatement ps = dbConnection.prepareStatement(insert);
            ps.setString(1, user.firstname);
            ps.setString(2, user.lastname);
            ps.setString(3, user.login);
            ps.setString(4, user.password);
            ps.setString(5, user.id_type);
            ps.setString(6, user.id_number);
            ps.setString(7, user.address);
            ps.setString(8, user.mobile_phone);
            ps.setString(9, user.home_phone);
            ps.setString(10, "user.category");

            ps.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
