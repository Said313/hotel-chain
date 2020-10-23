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

        String insert = "INSERT INTO " + UsersTableColumns.TABLE_NAME + "("
                + UsersTableColumns.FIRSTNAME + ","
                + UsersTableColumns.LASTNAME + ","
                + UsersTableColumns.LOGIN + ","
                + UsersTableColumns.PASSWORD + ","
                + UsersTableColumns.ID_TYPE + ","
                + UsersTableColumns.ID_NUMBER + ","
                + UsersTableColumns.ADDRESS + ","
                + UsersTableColumns.MOBILE_PHONE + ","
                + UsersTableColumns.HOME_PHONE + ")"
                + "VALUES(?,?,?,?,?,?,?,?,?)";

        try
        {
            PreparedStatement ps = dbConnection.prepareStatement(insert);
            ps.setString(1, user.firstname);
            ps.setString(2, user.lastname);
            ps.setString(3, user.login);
            ps.setString(4, user.password);
            ps.setInt(5, user.id_type.getId());
            ps.setString(6, user.id_number);
            ps.setString(7, user.address);
            ps.setString(8, user.mobile_phone);
            ps.setString(9, user.home_phone);

            ps.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
