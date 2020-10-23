package hotel.chain.app;

public class Guest extends User{
    public GuestCategories category;

    public Guest(User user, GuestCategories category){
        super(user);
        this.category = category;
    }
}
