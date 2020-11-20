package hotel.chain.app.database;

import hotel.chain.app.constants.authorization.*;
import hotel.chain.app.controllers.profile.ProfileEditRequest;
import hotel.chain.app.roles.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfileDBHandler extends DBConfigs{

    private Connection dbConnection;

    public ProfileDBHandler(){
        try{
            String url = "jdbc:mysql://" + dbHost + "/" + dbName + "?serverTimezone=Asia/Almaty";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            dbConnection = DriverManager.getConnection(url, dbLogin, dbPassword);
        } catch (ClassNotFoundException | SQLException | NoSuchMethodException |
                IllegalAccessException | InvocationTargetException | InstantiationException e){

            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editProfile(ProfileEditRequest parser) {



        String userUpdate =
                    "UPDATE "
                            + UsersTableColumns.TABLE_NAME
                    + " SET "
                            + UsersTableColumns.FIRSTNAME + " = ?, "
                            + UsersTableColumns.LASTNAME + " = ?, "
                            + UsersTableColumns.LOGIN + " = ?, "
                            + UsersTableColumns.PASSWORD + " = ?, "
                            + UsersTableColumns.ID_TYPE + " = ?, "
                            + UsersTableColumns.ID_NUMBER + " = ?, "
                            + UsersTableColumns.ADDRESS + " = ?, "
                            + UsersTableColumns.MOBILE_PHONE + " = ?, "
                            + UsersTableColumns.HOME_PHONE + " = ?, "
                            + UsersTableColumns.TYPE + " = ? "
                    + "WHERE "
                            + UsersTableColumns.ID + " = ?";
        
        String guestUpdate =
                    " UPDATE "
                            + GuestsTableColumns.TABLE_NAME
                    + " SET "
                            + GuestsTableColumns.CATEGORY + " = ? "
                    + "WHERE "
                            + GuestsTableColumns.ID + " = ?";


        PreparedStatement uups = null;
        PreparedStatement gups = null;

        try {
             uups = dbConnection.prepareStatement(userUpdate);
                uups.setString(1, parser.getFirstname());
                uups.setString(2, parser.getLastname());
                uups.setString(3, parser.getLogin());
                uups.setString(4, parser.getPassword());
                uups.setInt(5, parser.getId_type().getId());
                uups.setString(6, parser.getId_number());
                uups.setString(7, parser.getAddress());
                uups.setString(8, parser.getMobile_phone());
                uups.setString(9, parser.getHome_phone());
                uups.setInt(10, parser.getUserType().getId());
                uups.setInt(11, parser.getUserId());

            uups.executeUpdate();


            GuestCategories category = parser.getGuestCategory();

            gups = dbConnection.prepareStatement(guestUpdate);
            gups.setInt(1, category.getId());
            gups.setInt(2, parser.getUserId());

            if (parser.getUserType() == UserType.GUEST) {
                gups.executeUpdate();
            }
            




        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                gups.close();
                uups.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }


    }



}
