package hotel.chain.app.constants.bookings;

public enum BookingState {

    PAST("PAST", 1),
    CURRENT("CURRENT", 2),
    COMING("COMING", 3);

    private String name;
    private int id;

    BookingState(String name, int id) {
        this.name = name;
        this.id = id;
    };

    public String getName(){return name;}
    public int getId(){return id;}

}
