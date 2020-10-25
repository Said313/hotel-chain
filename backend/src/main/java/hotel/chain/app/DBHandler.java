package hotel.chain.app;

import hotel.chain.app.constants.DBConfigs;
import hotel.chain.app.constants.GuestsTableColumns;
import hotel.chain.app.constants.UsersTableColumns;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class DBHandler extends DBConfigs {
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

    public void signUpGuest(Guest guest){

        User user = new User(guest);

        String insertUser = "INSERT INTO " + UsersTableColumns.TABLE_NAME + "("
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

        String selectUser = "SELECT id FROM " + UsersTableColumns.TABLE_NAME + " WHERE "
                + UsersTableColumns.LOGIN + " = ?";

        String insertGuest = "INSERT INTO " + GuestsTableColumns.TABLE_NAME + "("
                + GuestsTableColumns.ID + ","
                + GuestsTableColumns.CATEGORY + ")"
                + "VALUES(?,?)";

        try
        {
            PreparedStatement psInsertUser = dbConnection.prepareStatement(insertUser);
                psInsertUser.setString(1, user.firstname);
                psInsertUser.setString(2, user.lastname);
                psInsertUser.setString(3, user.login);
                psInsertUser.setString(4, user.password);
                psInsertUser.setInt(5, user.id_type.getId());
                psInsertUser.setString(6, user.id_number);
                psInsertUser.setString(7, user.address);
                psInsertUser.setString(8, user.mobile_phone);
                psInsertUser.setString(9, user.home_phone);
                psInsertUser.executeUpdate();


            PreparedStatement psSelectUser = dbConnection.prepareStatement(selectUser);
                psSelectUser.setString(1, user.login);
                ResultSet rs = psSelectUser.executeQuery();
                rs.next();
                int userID = rs.getInt(UsersTableColumns.ID);



            PreparedStatement psInsertGuest = dbConnection.prepareStatement(insertGuest);
                psInsertGuest.setInt(1, userID);
                psInsertGuest.setString(2, guest.category.getName());
                psInsertGuest.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
