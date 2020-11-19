package hotel.chain.app.database;

import hotel.chain.app.constants.authorization.*;
import hotel.chain.app.controllers.authorization.SignupRequest;
import hotel.chain.app.roles.Guest;
import hotel.chain.app.roles.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class AuthDBHandler extends DBConfigs {
    private Connection dbConnection;
    private SignupRequest sur;

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

    public void closeConnection() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getDbConnection(){
        return dbConnection;
    }

    public void signUp(SignupRequest sur){

        this.sur = sur;
        int id = signUpUser();

        UserType userType = sur.getUserType();
        switch (userType){
            case GUEST: signUpGuest(id); break;
            case MANAGER:
            case DESK_CLERK:
                signUpEmployee(id); break;
        }


    }

    private void signUpEmployee(int userId) {

        int hotelId = sur.getHotelId();

        String insertEmployee = "INSERT INTO " + EmployeeTableColumns.TABLE_NAME + "("
                + EmployeeTableColumns.ID + ","
                + EmployeeTableColumns.HOTEL + ")"
                + "VALUES(?,?)";

        PreparedStatement psInsertEmployee = null;

        try {
            psInsertEmployee = dbConnection.prepareStatement(insertEmployee);
            psInsertEmployee.setInt(1, userId);
            psInsertEmployee.setInt(2, hotelId);
            psInsertEmployee.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        } finally {

            try {
                psInsertEmployee.close();

            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    public int signUpUser() {

        User user = new User(
                sur.getFirstname(),
                sur.getLastname(),
                sur.getLogin(),
                sur.getPassword(),
                sur.getId_type(),
                sur.getId_number(),
                sur.getAddress(),
                sur.getMobile_phone(),
                sur.getHome_phone(),
                sur.getUserType()
        );

        String insertUser = "INSERT INTO " + UsersTableColumns.TABLE_NAME + "("
                + UsersTableColumns.FIRSTNAME + ","
                + UsersTableColumns.LASTNAME + ","
                + UsersTableColumns.LOGIN + ","
                + UsersTableColumns.PASSWORD + ","
                + UsersTableColumns.ID_TYPE + ","
                + UsersTableColumns.ID_NUMBER + ","
                + UsersTableColumns.ADDRESS + ","
                + UsersTableColumns.MOBILE_PHONE + ","
                + UsersTableColumns.HOME_PHONE + ","
                + UsersTableColumns.TYPE + ")"
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement psInsertUser = null;



        try {

            psInsertUser = dbConnection.prepareStatement(insertUser);
                psInsertUser.setString(1, user.firstname);
                psInsertUser.setString(2, user.lastname);
                psInsertUser.setString(3, user.login);
                psInsertUser.setString(4, user.password);
                psInsertUser.setInt(5, user.id_type.getId());
                psInsertUser.setString(6, user.id_number);
                psInsertUser.setString(7, user.address);
                psInsertUser.setString(8, user.mobile_phone);
                psInsertUser.setString(9, user.home_phone);
                psInsertUser.setInt(10, user.type.getId());
            psInsertUser.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        } finally {
            try {
                psInsertUser.close();

            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }

        return getUserByLogin(user.login).id;
    }

    public void signUpGuest(int id){


        String insertGuest = "INSERT INTO " + GuestsTableColumns.TABLE_NAME + "("
                + GuestsTableColumns.ID + ","
                + GuestsTableColumns.CATEGORY + ")"
                + "VALUES(?,?)";

        PreparedStatement psInsertGuest = null;

        try {
            psInsertGuest = dbConnection.prepareStatement(insertGuest);
                psInsertGuest.setInt(1, id);
                psInsertGuest.setInt(2, sur.getGuestCategory().getId());
            psInsertGuest.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        } finally {

            try {
                psInsertGuest.close();

            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    public User getUserByLogin(String login) {

        String selectUser = "SELECT * FROM " + UsersTableColumns.TABLE_NAME + " WHERE "
                + UsersTableColumns.LOGIN + " = ?";

        User user = new User();

        try
        {
            PreparedStatement psSelectUser = dbConnection.prepareStatement(selectUser);
                psSelectUser.setString(1, login);
                ResultSet rs = psSelectUser.executeQuery();

            if (rs.next()) {
                user.id = rs.getInt(UsersTableColumns.ID);
                user.address = rs.getString(UsersTableColumns.ADDRESS);
                user.firstname = rs.getString(UsersTableColumns.FIRSTNAME);
                user.home_phone = rs.getString(UsersTableColumns.HOME_PHONE);
                user.id_number = rs.getString(UsersTableColumns.ID_NUMBER);
                user.id_type = Id_type.getByName(rs.getString(UsersTableColumns.ID_TYPE));
                user.lastname = rs.getString(UsersTableColumns.LASTNAME);
                user.login = rs.getString(UsersTableColumns.LOGIN);
                user.mobile_phone = rs.getString(UsersTableColumns.MOBILE_PHONE);
                user.password = rs.getString(UsersTableColumns.PASSWORD);
                user.type = UserType.getById(rs.getInt(UsersTableColumns.TYPE));
            }

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }

        return user;
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
