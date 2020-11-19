package hotel.chain.app.constants.bookings;

public enum BookingState {

    EMPTY("empty"),
    PAST("past"),
    INCOMING("incoming"),
    ALL("all");

    private String name;

    BookingState(String name) {
        this.name = name;
    };

    public String getName(){return name;}

    public static BookingState getByName(String name){

        BookingState res = BookingState.EMPTY;
        for (BookingState state : BookingState.values()){
            if (name.equals(state.name)){
                res = state;
            }
        }
        return res;
    }

}
