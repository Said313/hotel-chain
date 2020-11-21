package hotel.chain.app.entities;

import java.sql.Date;

public class BookingForProfile {
    private int bookingId;
    private Date checkIn;
    private Date checkOut;
    private String name;
    private float bill;
    private String roomTypeName;
    private String hotelName;
    private String hotelAddress;
    private String city;
    private String roomNumber;
    
    public BookingForProfile(int bookingId, Date checkIn, Date checkOut, String name, float bill, String roomTypeName,
                             String hotelName, String hotelAddress, String city, String roomNumber) {

        this.bookingId = bookingId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.name = name;
        this.bill = bill;
        this.roomTypeName = roomTypeName;
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.city = city;
        this.roomNumber = roomNumber;
    }
}


