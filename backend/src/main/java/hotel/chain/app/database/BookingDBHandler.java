package hotel.chain.app.database;

import hotel.chain.app.constants.bookings.*;
import hotel.chain.app.controllers.bookings.CheckInOutRequest;
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

        String sqlBookings = "INSERT INTO " + BookingsTableColumns.TABLE_NAME + "("
                + BookingsTableColumns.ROOMS_id + ", "
                + BookingsTableColumns.GUESTS_USER_id + ", "
                + BookingsTableColumns.CHECKIN + ", "
                + BookingsTableColumns.CHECKOUT + ", "
                + BookingsTableColumns.BILL + ", "
                + BookingsTableColumns.DURING + ")"
                + "VALUES (?, ?, ?, ?, ?, ?)";


        String sqlLastBookingID =
                "SELECT "
                    + "MAX(" + BookingsTableColumns.ID + ") "
                + "FROM "
                    + BookingsTableColumns.TABLE_NAME;

        String sqlBookingsHaveAdditionalServices =
                "INSERT INTO "
                        + BookingsHaveAdditionalServicesTable.TABLE_NAME + "("
                            + BookingsHaveAdditionalServicesTable.BOOKINGS_id + ", "
                            + BookingsHaveAdditionalServicesTable.ADDITIONAL_SERVICES_id + " "
                        + ") "
                + "VALUES(?, ?)";



        PreparedStatement psBookings  = null;
        PreparedStatement psLastBookingID = null;
        PreparedStatement psBookingsHaveAdditionalServices = null;

        ResultSet rsLastBookingID = null;

        try
        {
            int seasonID;
            if (booking.during.id == 0){
                seasonID = defaultSeason();
            } else {
                seasonID = booking.during.id;
            }



            psBookings = dbConnection.prepareStatement(sqlBookings);
                psBookings.setInt(1, roomId);
                psBookings.setInt(2, booking.guestId);
                psBookings.setDate(3, new Date(booking.checkIn.getTime()));
                psBookings.setDate(4, new Date(booking.checkout.getTime()));
                psBookings.setFloat(5, booking.bill);
                psBookings.setInt(6, seasonID);
            psBookings.executeUpdate();

            psLastBookingID = dbConnection.prepareStatement(sqlLastBookingID);
            rsLastBookingID = psLastBookingID.executeQuery();
            rsLastBookingID.next();
            int lastBookingID = rsLastBookingID.getInt("MAX(" + BookingsTableColumns.ID + ")");

            for (AdditionalService additionalService : booking.additionalServices)
            {
                psBookingsHaveAdditionalServices = dbConnection.prepareStatement(sqlBookingsHaveAdditionalServices);
                    psBookingsHaveAdditionalServices.setInt(1, lastBookingID);
                    psBookingsHaveAdditionalServices.setInt(2, additionalService.getServiceID());
                psBookingsHaveAdditionalServices.executeUpdate();
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try
            {
                if (rsLastBookingID != null) {rsLastBookingID.close();}
                if (psBookings!= null) {psBookings.close();}
                if (psBookingsHaveAdditionalServices != null) {psBookingsHaveAdditionalServices.close();}
                if (psLastBookingID != null) {psLastBookingID.close();}
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

        String selectAdditionalServices =
                "SELECT * "
                + "FROM "
                    + AdditionalServicesTableColumns.TABLE_NAME + " "
                + "WHERE "
                    + AdditionalServicesTableColumns.HOTELS_id + " = ?";


        PreparedStatement psRoomType = null;
        PreparedStatement psRoom = null;
        PreparedStatement psBookings = null;
        PreparedStatement psHotel = null;
        PreparedStatement psSeason = null;
        PreparedStatement psAdditionalServices = null;

        ResultSet rsHotel = null;
        ResultSet rsRoomTypes = null;
        ResultSet rsRooms = null;
        ResultSet rsBookings = null;
        ResultSet rsSeasons = null;
        ResultSet rsAdditionalServices = null;

        try {
            psHotel = dbConnection.prepareStatement(selectHotels);
            psHotel.setString(1, dest);

            psRoomType = dbConnection.prepareStatement(selectRoomTypes);
            psRoom = dbConnection.prepareStatement(selectRooms);
            psBookings = dbConnection.prepareStatement(selectBookings);
            psSeason = dbConnection.prepareStatement(selectSeasons);
            psAdditionalServices = dbConnection.prepareStatement(selectAdditionalServices);


            rsHotel = psHotel.executeQuery();

            while (rsHotel.next())
            {
                int hotelid = rsHotel.getInt(HotelsTableColumns.ID);
                String hotelName = rsHotel.getString(HotelsTableColumns.NAME);
                String address = rsHotel.getString(HotelsTableColumns.ADDRESS);
                String city = rsHotel.getString(HotelsTableColumns.CITY);
                String description = rsHotel.getString(HotelsTableColumns.DESCRIPTION);

                psAdditionalServices.setInt(1, hotelid);
                rsAdditionalServices = psAdditionalServices.executeQuery();

                ArrayList<AdditionalService> additionalServices = new ArrayList<>();

                while (rsAdditionalServices.next())
                {
                    AdditionalService additionalService = new AdditionalService(
                            rsAdditionalServices.getInt(AdditionalServicesTableColumns.ID),
                            rsAdditionalServices.getString(AdditionalServicesTableColumns.NAME),
                            rsAdditionalServices.getFloat(AdditionalServicesTableColumns.PRICE)
                    );
                    additionalServices.add(additionalService);
                }

                psSeason.setInt(1, hotelid);
                rsSeasons = psSeason.executeQuery();

                ArrayList<Season> seasons = new ArrayList<>();

                while (rsSeasons.next())
                {
                    Season season = new Season(
                            rsSeasons.getInt(SeasonsTableColumns.TABLE_NAME + "." + SeasonsTableColumns.ID),
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

                Hotel h = new Hotel(hotelid, hotelName, address, city, description, roomTypes, seasons, additionalServices);
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

                if (psBookings != null) {psBookings.close();}
                if (psHotel!=null) {psHotel.close();}
                if (psRoom != null) {psRoom.close();}
                if (psRoomType != null) {psRoomType.close();}

                if (rsBookings != null) {rsBookings.close();}
                if (rsHotel!=null) {rsHotel.close();}
                if (rsRooms!=null){rsRooms.close();}
                if (rsRoomTypes!=null) {rsRoomTypes.close();}

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

        String sqlBookings = "DELETE FROM "
                        + BookingsTableColumns.TABLE_NAME
                    + " WHERE "
                        + BookingsTableColumns.ID + " = ?";

        String sqlBookingsHaveAdditionalServices =
                "DELETE FROM "
                    + BookingsHaveAdditionalServicesTable.TABLE_NAME + " "
                + "WHERE "
                    + BookingsHaveAdditionalServicesTable.BOOKINGS_id + " = ?";

        PreparedStatement psBookings = null;
        PreparedStatement psBookingsHaveAdditionalServices = null;

        try {
            psBookingsHaveAdditionalServices = dbConnection.prepareStatement(sqlBookingsHaveAdditionalServices);
            psBookingsHaveAdditionalServices.setInt(1, bookingID);
            psBookingsHaveAdditionalServices.executeUpdate();

            psBookings = dbConnection.prepareStatement(sqlBookings);
            psBookings.setInt(1, bookingID);
            psBookings.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (psBookings != null) { psBookings.close(); }
                if (psBookingsHaveAdditionalServices != null ) { psBookingsHaveAdditionalServices.close(); }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public Receipt getReceipt(CheckInOutRequest parser) {

        Receipt receipt = new Receipt();

        String sqlBooking =
                "SELECT * FROM "
                    + BookingsTableColumns.TABLE_NAME + ", "
                    + RoomsTableColumns.TABLE_NAME + ", "
                    + RoomTypesTableColumns.TABLE_NAME + " "
                + "WHERE "
                    + BookingsTableColumns.TABLE_NAME + "." + BookingsTableColumns.ROOMS_id
                        + " = "
                    + RoomsTableColumns.TABLE_NAME + "." + RoomsTableColumns.ID
                + " AND "
                    + RoomsTableColumns.TABLE_NAME + "." + RoomsTableColumns.ID
                        + " = "
                    + RoomTypesTableColumns.TABLE_NAME + "." + RoomTypesTableColumns.ID
                + " AND "
                    + BookingsTableColumns.TABLE_NAME + "." + BookingsTableColumns.DURING
                        + " = "
                    + SeasonsTableColumns.TABLE_NAME + "." + SeasonsTableColumns.ID
                + " AND "
                    + BookingsTableColumns.TABLE_NAME + "." + BookingsTableColumns.ID
                        + " = ?";


        String sqlAdditionalServices =
                "SELECT * FROM "
                    + BookingsHaveAdditionalServicesTable.TABLE_NAME + ", "
                    + AdditionalServicesTableColumns.TABLE_NAME + " "
                + "WHERE "
                    + BookingsHaveAdditionalServicesTable.TABLE_NAME + "." + BookingsHaveAdditionalServicesTable.ADDITIONAL_SERVICES_id
                        + " = "
                    + AdditionalServicesTableColumns.TABLE_NAME + "." + AdditionalServicesTableColumns.ID
                + " AND "
                    + BookingsTableColumns.TABLE_NAME + "." + BookingsTableColumns.ID
                        + " = ?";

        PreparedStatement psBooking = null;
        PreparedStatement psAdditionalServices = null;
        ResultSet rsBooking = null;
        ResultSet rsAdditionalServices = null;

        try {
            psAdditionalServices = dbConnection.prepareStatement(sqlAdditionalServices);
            psAdditionalServices.setInt(1, parser.getBookingID());
            rsAdditionalServices = psAdditionalServices.executeQuery();

            ArrayList<AdditionalService> additionalServices = new ArrayList<>();
            while (rsAdditionalServices.next()){
                AdditionalService additionalService = new AdditionalService(
                        rsAdditionalServices.getInt(AdditionalServicesTableColumns.TABLE_NAME + "." + AdditionalServicesTableColumns.ID),
                        rsAdditionalServices.getString(AdditionalServicesTableColumns.TABLE_NAME + "." + AdditionalServicesTableColumns.NAME),
                        rsAdditionalServices.getFloat(AdditionalServicesTableColumns.TABLE_NAME + "." + AdditionalServicesTableColumns.PRICE)
                );

                additionalServices.add(additionalService);
            }


            psBooking = dbConnection.prepareStatement(sqlBooking);
            psBooking.setInt(1, parser.getBookingID());
            rsBooking = psBooking.executeQuery();
            rsBooking.next();

            Season season = new Season(
                    rsBooking.getInt(SeasonsTableColumns.TABLE_NAME + "." + SeasonsTableColumns.ID),
                    rsBooking.getString(SeasonsTableColumns.TABLE_NAME + "." + SeasonsTableColumns.SEASON_NAME),
                    rsBooking.getDate(SeasonsTableColumns.TABLE_NAME + "." + SeasonsTableColumns.STARTS),
                    rsBooking.getDate(SeasonsTableColumns.TABLE_NAME + "." + SeasonsTableColumns.ENDS),
                    rsBooking.getFloat(SeasonsTableColumns.TABLE_NAME + "." + SeasonsTableColumns.PRICE_FACTOR)
                );

            Booking booking = new Booking(
                rsBooking.getInt(BookingsTableColumns.TABLE_NAME + "." + BookingsTableColumns.ID),
                rsBooking.getInt(BookingsTableColumns.TABLE_NAME + "." + BookingsTableColumns.GUESTS_USER_id),
                season,
                rsBooking.getDate(BookingsTableColumns.TABLE_NAME + "." + BookingsTableColumns.CHECKIN),
                rsBooking.getDate(BookingsTableColumns.TABLE_NAME + "." + BookingsTableColumns.CHECKOUT),
                rsBooking.getFloat(BookingsTableColumns.TABLE_NAME + "." + BookingsTableColumns.BILL),
                additionalServices
            );

            //receipt = new Receipt(booking, roomType);



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rsBooking != null) {rsBooking.close();}
                if (psBooking != null) {psBooking.close();}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return receipt;
    }


    public int defaultSeason(){

        String sql =
                "SELECT "
                    + SeasonsTableColumns.ID + " " +
                "FROM "
                    + SeasonsTableColumns.TABLE_NAME + " " +
                "WHERE "
                    + SeasonsTableColumns.SEASON_NAME + " = ?";

        int res = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = dbConnection.prepareStatement(sql);
            ps.setString(1, "no season");
            rs = ps.executeQuery();
            rs.next();

            res = rs.getInt(SeasonsTableColumns.ID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {rs.close();}
                if (ps != null) {ps.close();}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return res;
    }
}
