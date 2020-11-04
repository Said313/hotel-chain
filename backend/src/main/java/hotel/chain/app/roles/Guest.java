package hotel.chain.app.roles;

import hotel.chain.app.constants.authorization.GuestCategories;
import hotel.chain.app.constants.authorization.Id_type;

public class Guest extends User {
    public GuestCategories category;

    public Guest(int id, String firstname, String lastname, String login, String password, Id_type id_type,
                String id_number, String address, String mobile_phone, String home_phone, GuestCategories category){
        super(id, firstname, lastname, login, password, id_type, id_number, address, mobile_phone, home_phone);
        this.category = category;
    }

    public Guest(String firstname, String lastname, String login, String password, Id_type id_type,
                 String id_number, String address, String mobile_phone, String home_phone, GuestCategories category){
        super(firstname, lastname, login, password, id_type, id_number, address, mobile_phone, home_phone);
        this.category = category;
    }

    public Guest(User user, GuestCategories category){
        super(user);
        this.category = category;
    }

    public Guest() {

    }
}
