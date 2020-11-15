package hotel.chain.app.database;

import hotel.chain.app.constants.*;
import hotel.chain.app.constants.authorization.*;
import hotel.chain.app.roles.Guest;
import hotel.chain.app.roles.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;

public class AuthDBHandler extends DBConfigs {
    private Connection dbConnection;

    public AuthDBHandler(){
        try{
            String url = "jdbc:mysql://" + dbHost + "/" + dbName + "?serverTimezone=Asia/Almaty";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            dbConnection = DriverManager.getConnection(url, dbLogin, dbPassword);
        } catch (ClassNotFoundException | SQLException | NoSuchMethodException |
                IllegalAccessException | InvocationTargetException | InstantiationException e){

            e.printStackTrace();
        }
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
            int guestID = userID;



            PreparedStatement psInsertGuest = dbConnection.prepareStatement(insertGuest);
                psInsertGuest.setInt(1, guestID);
                psInsertGuest.setInt(2, guest.category.getId());
            psInsertGuest.executeUpdate();


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }







    public User findUserByLogin(String login){

        String selectUser = "SELECT * FROM " + UsersTableColumns.TABLE_NAME + " WHERE "
                + UsersTableColumns.LOGIN + " = ?";

        User user = null;

        try
        {
            PreparedStatement psSelectUser = dbConnection.prepareStatement(selectUser);
                psSelectUser.setString(1, login);
                ResultSet rs = psSelectUser.executeQuery();

                if (rs.next()) {

                    int id_type_int = rs.getInt(UsersTableColumns.ID_TYPE);
                    Id_type id_type;
                    switch (id_type_int) {
                        case 1:
                            id_type = Id_type.US_PASSPORT;
                            break;
                        case 2:
                            id_type = Id_type.DRIVING_LICENSE;
                            break;
                        default:
                            id_type = Id_type.NOT_PROVIDED;
                    }

                    user = new User(
                            rs.getInt(UsersTableColumns.ID),
                            rs.getString(UsersTableColumns.FIRSTNAME),
                            rs.getString(UsersTableColumns.LASTNAME),
                            rs.getString(UsersTableColumns.LOGIN),
                            rs.getString(UsersTableColumns.PASSWORD),
                            id_type,
                            rs.getString(UsersTableColumns.ID_NUMBER),
                            rs.getString(UsersTableColumns.ADDRESS),
                            rs.getString(UsersTableColumns.MOBILE_PHONE),
                            rs.getString(UsersTableColumns.HOME_PHONE)
                    );
                }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
            user = new User();
        }

        return user;
    }






    public Guest findGuestByLogin(String login){
        User user = findUserByLogin(login);
        Guest guest = null;
        GuestCategories category = null;


        String selectGuest = "SELECT * FROM " + GuestsTableColumns.TABLE_NAME + " WHERE "
                + GuestsTableColumns.ID + " = ?";


        try
        {
            PreparedStatement psSelectUser = dbConnection.prepareStatement(selectGuest);
            psSelectUser.setInt(1, user.id);
            ResultSet rs = psSelectUser.executeQuery();

            if (rs.next()){
                int cat = rs.getInt(GuestsTableColumns.CATEGORY);
                switch (cat){
                    case 2: category = GuestCategories.VIP;
                        break;
                    case 3: category = GuestCategories.GOVERNMENT;
                        break;
                    case 4: category = GuestCategories.MILITARY;
                        break;
                    default: category = GuestCategories.NONE;
                }
            }

            guest = new Guest(user, category);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
            guest = new Guest();
        }

        return guest;
    }









    public ResultSet executeSelect(String query){

        ResultSet rs = null;
        try{
            PreparedStatement ps = dbConnection.prepareStatement(query);
            rs = ps.executeQuery();

        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

        return rs;
    }
}
