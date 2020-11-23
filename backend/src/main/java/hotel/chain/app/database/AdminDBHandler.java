package hotel.chain.app.database;

import hotel.chain.app.constants.authorization.Id_type;
import hotel.chain.app.constants.authorization.UserType;
import hotel.chain.app.constants.authorization.UsersTableColumns;
import hotel.chain.app.constants.bookings.AdditionalServicesTableColumns;
import hotel.chain.app.constants.bookings.BookingsHaveAdditionalServicesTable;
import hotel.chain.app.constants.bookings.BookingsTableColumns;
import hotel.chain.app.constants.bookings.SeasonsTableColumns;
import hotel.chain.app.entities.AdditionalService;
import hotel.chain.app.entities.Booking;
import hotel.chain.app.entities.Season;
import hotel.chain.app.roles.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class AdminDBHandler extends DBConfigs {
    private Connection dbConnection;

    public AdminDBHandler() {
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


    public ArrayList<Booking> getAllBookings() {

        ArrayList<Booking> bookings = new ArrayList<>();


        String sqlBookings =
                "SELECT "
                            + BookingsTableColumns.TABLE_NAME + "." + BookingsTableColumns.ID + ", "
                            + BookingsTableColumns.TABLE_NAME + "." + BookingsTableColumns.GUESTS_USER_id + ", "
                            + BookingsTableColumns.TABLE_NAME + "." + BookingsTableColumns.CHECKIN + ", "
                            + BookingsTableColumns.TABLE_NAME + "." + BookingsTableColumns.CHECKOUT + ", "
                            + BookingsTableColumns.TABLE_NAME + "." + BookingsTableColumns.BILL + ", "
                            + SeasonsTableColumns.TABLE_NAME + "." + SeasonsTableColumns.ID + ", "
                            + SeasonsTableColumns.TABLE_NAME + "." + SeasonsTableColumns.SEASON_NAME + ", "
                            + SeasonsTableColumns.TABLE_NAME + "." + SeasonsTableColumns.PRICE_FACTOR + ", "
                            + SeasonsTableColumns.TABLE_NAME + "." + SeasonsTableColumns.STARTS + ", "
                            + SeasonsTableColumns.TABLE_NAME + "." + SeasonsTableColumns.ENDS + " "
                +   "FROM "
                            + BookingsTableColumns.TABLE_NAME + ", "
                            + SeasonsTableColumns.TABLE_NAME + " "
                +   "WHERE "
                            + BookingsTableColumns.TABLE_NAME + "." + BookingsTableColumns.DURING
                            + " = "
                            + SeasonsTableColumns.TABLE_NAME + "." + SeasonsTableColumns.ID;


        String sqlAdditionalServices =
                "SELECT "
                    + AdditionalServicesTableColumns.TABLE_NAME + "." + AdditionalServicesTableColumns.ID + ", "
                    + AdditionalServicesTableColumns.TABLE_NAME + "." + AdditionalServicesTableColumns.NAME + ", "
                    + AdditionalServicesTableColumns.TABLE_NAME + "." + AdditionalServicesTableColumns.PRICE + " "
                + "FROM "
                    + BookingsHaveAdditionalServicesTable.TABLE_NAME + ", "
                    + AdditionalServicesTableColumns.TABLE_NAME + " "
                + "WHERE "
                    + BookingsHaveAdditionalServicesTable.TABLE_NAME + "." + BookingsHaveAdditionalServicesTable.ADDITIONAL_SERVICES_id
                        + " = "
                    + AdditionalServicesTableColumns.TABLE_NAME + "." + AdditionalServicesTableColumns.ID + " "
                + "AND "
                    + BookingsHaveAdditionalServicesTable.TABLE_NAME + "." + BookingsHaveAdditionalServicesTable.BOOKINGS_id
                        + " = "
                    + "?";


        PreparedStatement psBookings = null;
        PreparedStatement psAdditionalServices = null;

        ResultSet rsBookings = null;
        ResultSet rsAdditionalServices = null;

        try {
            psBookings = dbConnection.prepareStatement(sqlBookings);
            rsBookings = psBookings.executeQuery();

            while (rsBookings.next()) {

                psAdditionalServices = dbConnection.prepareStatement(sqlAdditionalServices);
                    psAdditionalServices.setInt(1, rsBookings.getInt(BookingsTableColumns.ID));
                rsAdditionalServices = psAdditionalServices.executeQuery();

                ArrayList<AdditionalService> additionalServices = new ArrayList<>();
                while (rsAdditionalServices.next()) {
                    AdditionalService service = new AdditionalService(
                            rsAdditionalServices.getInt(AdditionalServicesTableColumns.ID),
                            rsAdditionalServices.getString(AdditionalServicesTableColumns.NAME),
                            rsAdditionalServices.getFloat(AdditionalServicesTableColumns.PRICE)
                    );
                    additionalServices.add(service);
                }

                Season season = new Season(
                        rsBookings.getInt(SeasonsTableColumns.TABLE_NAME + "." + SeasonsTableColumns.ID),
                        rsBookings.getString(SeasonsTableColumns.SEASON_NAME),
                        rsBookings.getDate(SeasonsTableColumns.STARTS),
                        rsBookings.getDate(SeasonsTableColumns.ENDS),
                        rsBookings.getFloat(SeasonsTableColumns.PRICE_FACTOR)
                );
                Booking booking = new Booking(
                        rsBookings.getInt(BookingsTableColumns.ID),
                        rsBookings.getInt(BookingsTableColumns.GUESTS_USER_id),
                        season,
                        rsBookings.getDate(BookingsTableColumns.CHECKIN),
                        rsBookings.getDate(BookingsTableColumns.CHECKOUT),
                        rsBookings.getFloat(BookingsTableColumns.BILL),
                        additionalServices
                );
                bookings.add(booking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rsBookings.close();
                rsAdditionalServices.close();
                psBookings.close();
                psAdditionalServices.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }

        return bookings;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();

        String sqlUsers = "SELECT * FROM " + UsersTableColumns.TABLE_NAME;

        PreparedStatement psUsers = null;
        ResultSet rsUsers = null;

        try {
            psUsers = dbConnection.prepareStatement(sqlUsers);
            rsUsers = psUsers.executeQuery();

            while (rsUsers.next()) {
                User user = new User(
                    rsUsers.getInt(UsersTableColumns.ID),
                        rsUsers.getString(UsersTableColumns.FIRSTNAME),
                        rsUsers.getString(UsersTableColumns.LASTNAME),
                        rsUsers.getString(UsersTableColumns.LOGIN),
                        rsUsers.getString(UsersTableColumns.PASSWORD),
                        Id_type.getById(rsUsers.getInt(UsersTableColumns.ID_TYPE)),
                        rsUsers.getString(UsersTableColumns.ID_NUMBER),
                        rsUsers.getString(UsersTableColumns.ADDRESS),
                        rsUsers.getString(UsersTableColumns.MOBILE_PHONE),
                        rsUsers.getString(UsersTableColumns.HOME_PHONE),
                        UserType.getById(rsUsers.getInt(UsersTableColumns.TYPE))
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rsUsers!=null) {rsUsers.close();}
                if (psUsers!=null) {psUsers.close();}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return users;
    }
}
