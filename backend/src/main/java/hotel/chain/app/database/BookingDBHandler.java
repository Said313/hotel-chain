package hotel.chain.app.database;

import hotel.chain.app.constants.bookings.*;
import hotel.chain.app.entities.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class BookingDBHandler extends DBConfigs {
    private Connection dbConnection;

    public BookingDBHandler(){
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

    public void createBooking(int roomId, Booking booking) {
        String sql = "INSERT INTO " + BookingsTableColumns.TABLE_NAME + "("
                + BookingsTableColumns.ROOMS_id + ", "
                + BookingsTableColumns.GUESTS_USER_id + ", "
                + BookingsTableColumns.CHECKIN + ", "
                + BookingsTableColumns.CHECKOUT + ", "
                + BookingsTableColumns.BILL + ")"
                + "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps  = null;
        try
        {
            ps = dbConnection.prepareStatement(sql);
                ps.setInt(1, roomId);
                ps.setInt(2, booking.guestId);
                ps.setDate(3, new Date(booking.checkIn.getTime()));
                ps.setDate(4, new Date(booking.checkout.getTime()));
                ps.setFloat(5, booking.bill);
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try
            {
                dbConnection.close();
                ps.close();
            }
            catch (SQLException | NullPointerException e)
            {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Hotel> getAvailableHotels(String dest, java.util.Date start, java.util.Date end) {
        ArrayList<Hotel> hotels = new ArrayList<>();

        String selectHotels = "SELECT * FROM " + HotelsTableColumns.TABLE_NAME
                + " WHERE " + HotelsTableColumns.CITY + " = ?";

        String selectRoomTypes = "SELECT * FROM " + RoomTypesTableColumns.TABLE_NAME
                + " WHERE " + RoomTypesTableColumns.HOTELS_ID + " = ?";
        
        String selectRooms = "SELECT * FROM " + RoomsTableColumns.TABLE_NAME
                + " WHERE " + RoomsTableColumns.ROOM_TYPE + " = ?";

        String selectBookings = "SELECT * FROM " + BookingsTableColumns.TABLE_NAME
                + " WHERE " + BookingsTableColumns.ROOMS_id + " = ? " +
                "AND " + BookingsTableColumns.CHECKOUT + " > ? " +
                "AND " + BookingsTableColumns.CHECKIN + " < ? ";

        String selectSeasons =
                "SELECT "
                        + HotelsTableColumns.TABLE_NAME + "." + HotelsTableColumns.ID + ", "
                        + SeasonsTableColumns.TABLE_NAME + "." + SeasonsTableColumns.ID + ", "
                        + SeasonsTableColumns.TABLE_NAME + "." + SeasonsTableColumns.SEASON_NAME + ", "
                        + SeasonsTableColumns.TABLE_NAME + "." + SeasonsTableColumns.STARTS + ", "
                        + SeasonsTableColumns.TABLE_NAME + "." + SeasonsTableColumns.ENDS + ", "
                        + SeasonsTableColumns.TABLE_NAME + "." + SeasonsTableColumns.PRICE_FACTOR + " " +
                "FROM "
                        + HotelsTableColumns.TABLE_NAME + ", "
                        + HotelsHaveSeasonsTableColumns.TABLE_NAME + ", "
                        + SeasonsTableColumns.TABLE_NAME + " " +
                "WHERE "
                        + HotelsTableColumns.TABLE_NAME + "." + HotelsTableColumns.ID
                        + " = "
                        + HotelsHaveSeasonsTableColumns.TABLE_NAME + "." + HotelsHaveSeasonsTableColumns.HOTELS_id + " " +
                    "AND "
                        + SeasonsTableColumns.TABLE_NAME + "." + SeasonsTableColumns.ID
                        + " = "
                        + HotelsHaveSeasonsTableColumns.TABLE_NAME + "." + HotelsHaveSeasonsTableColumns.SEASONS_id + " " +
                    "AND "
                        + HotelsTableColumns.TABLE_NAME + "." + HotelsTableColumns.ID + " = ?;";


        PreparedStatement psRoomType = null;
        PreparedStatement psRoom = null;
        PreparedStatement psBookings = null;
        PreparedStatement psHotel = null;
        PreparedStatement psSeason = null;

        ResultSet rsHotel = null;
        ResultSet rsRoomTypes = null;
        ResultSet rsRooms = null;
        ResultSet rsBookings = null;
        ResultSet rsSeasons = null;

        try {
            psHotel = dbConnection.prepareStatement(selectHotels);
            psHotel.setString(1, dest);

            psRoomType = dbConnection.prepareStatement(selectRoomTypes);
            psRoom = dbConnection.prepareStatement(selectRooms);
            psBookings = dbConnection.prepareStatement(selectBookings);
            psSeason = dbConnection.prepareStatement(selectSeasons);


            rsHotel = psHotel.executeQuery();

            while (rsHotel.next())
            {
                int hotelid = rsHotel.getInt(HotelsTableColumns.ID);
                String hotelName = rsHotel.getString(HotelsTableColumns.NAME);
                String address = rsHotel.getString(HotelsTableColumns.ADDRESS);
                String city = rsHotel.getString(HotelsTableColumns.CITY);
                String description = rsHotel.getString(HotelsTableColumns.DESCRIPTION);

                psSeason.setInt(1, hotelid);
                rsSeasons = psSeason.executeQuery();

                ArrayList<Season> seasons = new ArrayList<>();

                while (rsSeasons.next())
                {
                    Season season = new Season(
                            rsSeasons.getInt(SeasonsTableColumns.ID),
                            rsSeasons.getString(SeasonsTableColumns.SEASON_NAME),
                            rsSeasons.getDate(SeasonsTableColumns.STARTS),
                            rsSeasons.getDate(SeasonsTableColumns.ENDS),
                            rsSeasons.getFloat(SeasonsTableColumns.PRICE_FACTOR)
                    );

                    seasons.add(season);
                }
                
                ArrayList<RoomType> roomTypes = new ArrayList<>();
                psRoomType.setInt(1, hotelid);
                

                rsRoomTypes = psRoomType.executeQuery();
                while (rsRoomTypes.next())
                {
                    int roomTypeId = rsRoomTypes.getInt(RoomTypesTableColumns.ID);
                    String roomTypeName = rsRoomTypes.getString(RoomTypesTableColumns.NAME);
                    float size = rsRoomTypes.getFloat(RoomTypesTableColumns.SIZE);
                    int capacity = rsRoomTypes.getInt(RoomTypesTableColumns.CAPACITY);
                    float fixedPrice = rsRoomTypes.getFloat(RoomTypesTableColumns.ROOM_TYPE_PRICE_FIXED);
                    
                    ArrayList<Room> rooms = new ArrayList<>();
                    psRoom.setInt(1, roomTypeId);

                    rsRooms = psRoom.executeQuery();
                    while (rsRooms.next())
                    {
                        int roomid = rsRooms.getInt(RoomsTableColumns.ID);
                        String number = rsRooms.getString(RoomsTableColumns.NUMBER);
                        int floor = rsRooms.getInt(RoomsTableColumns.FLOOR);
                        boolean isClean = rsRooms.getBoolean(RoomsTableColumns.ISCLEAN);
                        boolean isOccupied = rsRooms.getBoolean(RoomsTableColumns.ISOCCUPIED);

                        ArrayList<Booking> bookings = new ArrayList<>();
                        psBookings.setInt(1, roomid);
                        psBookings.setDate(2,  new java.sql.Date(start.getTime()));
                        psBookings.setDate(3, new java.sql.Date(end.getTime()));

                        rsBookings = psBookings.executeQuery();
                        if (DBConfigs.isEmpty(rsBookings))
                        {
                            Room room = new Room(roomid, number, floor, isClean, isOccupied, bookings);
                            rooms.add(room);
                        }
                    }

                    RoomType rt = new RoomType(roomTypeId, roomTypeName, size, capacity, fixedPrice, rooms);
                    roomTypes.add(rt);
                }

                Hotel h = new Hotel(hotelid, hotelName, address, city, description, roomTypes, seasons);
                hotels.add(h);
            }

            ArrayList<Hotel> hotels_copy = new ArrayList<>();

            for (Hotel hotel : hotels)
            {
                ArrayList<RoomType> roomTypes = new ArrayList<>();
                for (RoomType roomType : hotel.roomTypes)
                {
                    if (!roomType.rooms.isEmpty())
                    {
                        roomTypes.add(roomType);
                    }
                }
                hotel.roomTypes = roomTypes;
                if (!roomTypes.isEmpty())
                {
                    hotels_copy.add(hotel);
                }
            }

            hotels = hotels_copy;


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {

                psBookings.close();
                psHotel.close();
                psRoom.close();
                psRoomType.close();

                rsBookings.close();
                rsHotel.close();
                rsRooms.close();
                rsRoomTypes.close();

            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }

        }



        return hotels;
    }

    public ArrayList<BookingForProfile> getBookingsOfAGuest(int guestId, BookingState bookingState){

        ArrayList<BookingForProfile> bookings = new ArrayList<>();

        String filterByBookingState = "";
        switch (bookingState){
            case PAST: filterByBookingState = " AND " + BookingsTableColumns.CHECKOUT + " < current_date()"; break;
            case INCOMING: filterByBookingState = " AND " + BookingsTableColumns.CHECKOUT + " >= current_date()"; break;
        }

        //table names
        String bookingsTN = BookingsTableColumns.TABLE_NAME;
        String seasonsTN = SeasonsTableColumns.TABLE_NAME;
        String roomsTN = RoomsTableColumns.TABLE_NAME;
        String roomtypesTN = RoomTypesTableColumns.TABLE_NAME;
        String hotelsTN = HotelsTableColumns.TABLE_NAME;

        String sql = "SELECT "
                            + bookingsTN + "." + BookingsTableColumns.ID + " , "
                            + bookingsTN + "." + BookingsTableColumns.CHECKIN + " , "
                            + bookingsTN + "." + BookingsTableColumns.CHECKOUT + " , "
                            + seasonsTN + "." + SeasonsTableColumns.SEASON_NAME + " , "
                            + bookingsTN + "." + BookingsTableColumns.BILL + " , "
                            + roomtypesTN + "." + RoomTypesTableColumns.NAME + " , "
                            + hotelsTN + "." + HotelsTableColumns.NAME + " , "
                            + hotelsTN + "." + HotelsTableColumns.ADDRESS + " , "
                            + hotelsTN + "." + HotelsTableColumns.CITY + " , "
                            + roomsTN + "." + RoomsTableColumns.NUMBER
                    + " FROM "
                            + BookingsTableColumns.TABLE_NAME + " , "
                            + RoomsTableColumns.TABLE_NAME + " , "
                            + RoomTypesTableColumns.TABLE_NAME + " , "
                            + HotelsTableColumns.TABLE_NAME + " , "
                            + SeasonsTableColumns.TABLE_NAME
                    + " WHERE "
                            + bookingsTN + "." + BookingsTableColumns.ROOMS_id + " = " + roomsTN + "." + RoomsTableColumns.ID + " AND "
                            + roomsTN + "." + RoomsTableColumns.ROOM_TYPE + " = " + roomtypesTN + "." + RoomTypesTableColumns.ID + " AND "
                            + roomtypesTN + "." + RoomTypesTableColumns.HOTELS_ID + " = " + hotelsTN + "." + HotelsTableColumns.ID + " AND "
                            + bookingsTN + "." + BookingsTableColumns.DURING + " = " + seasonsTN + "." + SeasonsTableColumns.ID + " AND "
                            + bookingsTN + "." + BookingsTableColumns.GUESTS_USER_id + " = ? "
                            + filterByBookingState
                    + " ORDER BY "
                            + bookingsTN + "." + BookingsTableColumns.CHECKIN;


        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setInt(1, guestId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                BookingForProfile booking = new BookingForProfile(
                                rs.getInt(bookingsTN + "." + BookingsTableColumns.ID),
                                rs.getDate(bookingsTN + "." + BookingsTableColumns.CHECKIN),
                                rs.getDate(bookingsTN + "." + BookingsTableColumns.CHECKOUT),
                                rs.getString(seasonsTN + "." + SeasonsTableColumns.SEASON_NAME),
                                rs.getFloat(bookingsTN + "." + BookingsTableColumns.BILL),
                                rs.getString(roomtypesTN + "." + RoomTypesTableColumns.NAME),
                                rs.getString(hotelsTN + "." + HotelsTableColumns.NAME),
                                rs.getString(hotelsTN + "." + HotelsTableColumns.ADDRESS),
                                rs.getString(hotelsTN + "." + HotelsTableColumns.CITY),
                                rs.getString(roomsTN + "." + RoomsTableColumns.NUMBER)
                        );

                bookings.add(booking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return bookings;
    }

    public void cancelBooking(int bookingID) {

        String sql = "DELETE FROM "
                        + BookingsTableColumns.TABLE_NAME
                    + " WHERE "
                        + BookingsTableColumns.ID + " = ?";

        PreparedStatement ps = null;

        try {
            ps = dbConnection.prepareStatement(sql);
            ps.setInt(1, bookingID);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
