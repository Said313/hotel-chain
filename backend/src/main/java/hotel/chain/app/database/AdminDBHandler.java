package hotel.chain.app.database;

import hotel.chain.app.constants.bookings.BookingsTableColumns;
import hotel.chain.app.constants.bookings.SeasonsTableColumns;
import hotel.chain.app.entities.Booking;
import hotel.chain.app.entities.Season;

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


        String sql = "SELECT "
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

        ResultSet rs = null;

        try {
            rs = selectAll(sql);
            while (rs.next()) {

                Season season = new Season(
                        rs.getInt(SeasonsTableColumns.ID),
                        rs.getString(SeasonsTableColumns.SEASON_NAME),
                        rs.getDate(SeasonsTableColumns.STARTS),
                        rs.getDate(SeasonsTableColumns.ENDS),
                        rs.getFloat(SeasonsTableColumns.PRICE_FACTOR)
                );
                Booking booking = new Booking(
                        rs.getInt(BookingsTableColumns.ID),
                        rs.getInt(BookingsTableColumns.GUESTS_USER_id),
                        season,
                        rs.getDate(BookingsTableColumns.CHECKIN),
                        rs.getDate(BookingsTableColumns.CHECKOUT),
                        rs.getFloat(BookingsTableColumns.BILL)
                );
                bookings.add(booking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }

        return bookings;
    }

    public ResultSet selectAll(String sql) throws SQLException {
        PreparedStatement ps = dbConnection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        return rs;
    }
}
