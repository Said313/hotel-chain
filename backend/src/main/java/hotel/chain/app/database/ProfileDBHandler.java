package hotel.chain.app.database;

import hotel.chain.app.constants.authorization.*;
import hotel.chain.app.controllers.profile.ProfileEditRequest;
import hotel.chain.app.controllers.profile.ScheduleGetRequest;
import hotel.chain.app.controllers.profile.ScheduleSetRequest;
import hotel.chain.app.entities.Schedule;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

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

    public void setSchedule(ScheduleSetRequest parser) {

        String sql =
                "UPDATE "
                    + ScheduleTableColumns.TABLE_NAME + " "
                + "SET "
                        + ScheduleTableColumns.MONDAY_START + " = ?, "
                        + ScheduleTableColumns.MONDAY_END + " = ?, "
                        + ScheduleTableColumns.TUESDAY_START + " = ?, "
                        + ScheduleTableColumns.TUESDAY_END + " = ?, "
                        + ScheduleTableColumns.WEDNESDAY_START + " = ?, "
                        + ScheduleTableColumns.WEDNESDAY_END + " = ?, "
                        + ScheduleTableColumns.THURSDAY_START + " = ?, "
                        + ScheduleTableColumns.THURSDAY_END + " = ?, "
                        + ScheduleTableColumns.FRIDAY_START + " = ?, "
                        + ScheduleTableColumns.FRIDAY_END + " = ?, "
                        + ScheduleTableColumns.SATURDAY_START + " = ?, "
                        + ScheduleTableColumns.SATURDAY_END + " = ?, "
                        + ScheduleTableColumns.SUNDAY_START + " = ?, "
                        + ScheduleTableColumns.SUNDAY_END + " = ?, "
                        + ScheduleTableColumns.PAYROLL + " = ? "
                + "WHERE "
                        + ScheduleTableColumns.EMPLOYEES_user_id + " = ?";

        PreparedStatement ps = null;

        try {
            ps = dbConnection.prepareStatement(sql);
                ps.setInt(1, parser.getUserID());
                ps.setTime(1, parser.getMondayStart());
                ps.setTime(2, parser.getMondayEnd());
                ps.setTime(3, parser.getTuesdayStart());
                ps.setTime(4, parser.getTuesdayEnd());
                ps.setTime(5, parser.getWednesdayStart());
                ps.setTime(6, parser.getWednesdayEnd());
                ps.setTime(7, parser.getThursdayStart());
                ps.setTime(8, parser.getThursdayEnd());
                ps.setTime(9, parser.getFridayStart());
                ps.setTime(10, parser.getFridayEnd());
                ps.setTime(11, parser.getSaturdayStart());
                ps.setTime(12, parser.getSaturdayEnd());
                ps.setTime(13, parser.getSundayStart());
                ps.setTime(14, parser.getSundayEnd());
                ps.setFloat(15, parser.getPayroll());
                ps.setInt(16, parser.getUserID());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Schedule getSchedule(ScheduleGetRequest parser) {

        String sql =
                "SELECT * FROM "
                    + ScheduleTableColumns.TABLE_NAME + " "
                + "WHERE "
                    + ScheduleTableColumns.EMPLOYEES_user_id + " = ? ";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Schedule schedule = new Schedule();

        try {
            ps = dbConnection.prepareStatement(sql);
            ps.setInt(1, parser.getUserID());
            System.out.println(ps);
            rs = ps.executeQuery();
            rs.next();

            schedule = new Schedule(
                    rs.getInt(ScheduleTableColumns.EMPLOYEES_user_id),
                    rs.getTime(ScheduleTableColumns.MONDAY_START),
                    rs.getTime(ScheduleTableColumns.MONDAY_END),
                    rs.getTime(ScheduleTableColumns.TUESDAY_START),
                    rs.getTime(ScheduleTableColumns.TUESDAY_END),
                    rs.getTime(ScheduleTableColumns.WEDNESDAY_START),
                    rs.getTime(ScheduleTableColumns.WEDNESDAY_END),
                    rs.getTime(ScheduleTableColumns.THURSDAY_START),
                    rs.getTime(ScheduleTableColumns.THURSDAY_END),
                    rs.getTime(ScheduleTableColumns.FRIDAY_START),
                    rs.getTime(ScheduleTableColumns.FRIDAY_END),
                    rs.getTime(ScheduleTableColumns.SATURDAY_START),
                    rs.getTime(ScheduleTableColumns.SATURDAY_END),
                    rs.getTime(ScheduleTableColumns.SUNDAY_START),
                    rs.getTime(ScheduleTableColumns.SUNDAY_END),
                    rs.getFloat(ScheduleTableColumns.PAYROLL)
            );


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {rs.close();}
                if (ps != null) {ps.close();}
            } catch (SQLException e ) {
                e.printStackTrace();
            }
        }

        return schedule;
    }
}
