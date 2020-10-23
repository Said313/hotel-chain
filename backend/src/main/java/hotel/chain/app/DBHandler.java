package examplejaxrs;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHandler extends DBConfigs{
    private Connection dbConnection;

    public DBHandler() throws ClassNotFoundException, SQLException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {

        String url = "jdbc:mysql://localhost/hotel?serverTimezone=Asia/Almaty"; //DBConfigs
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

        dbConnection = DriverManager.getConnection(url, dbLogin, dbPassword);
    }

    public Connection getDbConnection(){
        return dbConnection;
    }

    public void signUpUser(String firstname, String lastname, String login, String password, String idtype,
                             String idnumber, String address, String mobilephone, String homephone, String category){

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
            PreparedStatement ps = getDbConnection().prepareStatement(insert);
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, login);
            ps.setString(4, password);
            ps.setString(5, idtype);
            ps.setString(6, idnumber);
            ps.setString(7, address);
            ps.setString(8, mobilephone);
            ps.setString(9, homephone);
            ps.setString(10, category);

            ps.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
