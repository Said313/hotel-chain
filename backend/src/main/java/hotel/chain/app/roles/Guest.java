package hotel.chain.app.roles;

import hotel.chain.app.constants.authorization.GuestCategories;
import hotel.chain.app.constants.authorization.Id_type;
import hotel.chain.app.constants.authorization.UserType;

public class Guest extends User {
    public GuestCategories category;

    public Guest(int id, String firstname, String lastname, String login, String password, Id_type id_type,
                String id_number, String address, String mobile_phone, String home_phone, GuestCategories category){
        super(id, firstname, lastname, login, password, id_type, id_number, address,
                mobile_phone, home_phone, UserType.GUEST);
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
        id = 0;
        firstname = "empty";
        lastname = "empty";
        login = "empty";
        password = "empty";
        id_type = Id_type.NOT_PROVIDED;
        id_number = "empty";
        address = "empty";
        mobile_phone = "empty";
        home_phone = "empty";
    }
}
