package hotel.chain.app.roles;

import hotel.chain.app.constants.authorization.Id_type;
import hotel.chain.app.constants.authorization.UserType;

public class User {
    public int id;
    public String firstname;
    public String lastname;
    public String login;
    public String password;
    public Id_type id_type;
    public String id_number;
    public String address;
    public String mobile_phone;
    public String home_phone;
    public UserType type;

    public User(int id, String firstname, String lastname, String login, String password, Id_type id_type,
                String id_number, String address, String mobile_phone, String home_phone, UserType type) {

        this(firstname, lastname, login, password, id_type, id_number, address, mobile_phone, home_phone, type);
        this.id = id;
    }

    public User(String firstname, String lastname, String login, String password, Id_type id_type,
                String id_number, String address, String mobile_phone, String home_phone, UserType type){
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.id_type = id_type;
        this.id_number = id_number;
        this.address = address;
        this.mobile_phone = mobile_phone;
        this.home_phone = home_phone;
        this.type = type;

    }

    public User(String firstname, String lastname, String login, String password, Id_type id_type,
                String id_number, String address, String mobile_phone, String home_phone){
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.id_type = id_type;
        this.id_number = id_number;
        this.address = address;
        this.mobile_phone = mobile_phone;
        this.home_phone = home_phone;

    }

    public User(User user){
        this.id = user.id;
        this.firstname = user.firstname;
        this.lastname = user.lastname;
        this.login = user.login;
        this.password = user.password;
        this.id_type = user.id_type;
        this.id_number = user.id_number;
        this.address = user.address;
        this.mobile_phone = user.mobile_phone;
        this.home_phone = user.home_phone;
        
    }

    public User(){
        id = 0;
        firstname = "empty";
        lastname = "empty";
        login = "empty";
        password = "empty";
        id_type = Id_type.EMPTY;
        id_number = "empty";
        address = "empty";
        mobile_phone = "empty";
        home_phone = "empty";
        type = UserType.EMPTY;
    }

    @Override
    public String toString() {
        return "USER{"
                + "\nid: " + id
                + "\nfirstname: " + firstname
                + "\nlastname: " + lastname
                + "\nlogin: " + login
                + "\npassword: " + password
                + "\nid_type: " + id_type
                + "\nid_number: " + id_number
                + "\naddress: " + address
                + "\nmobile_phone: " + mobile_phone
                + "\nhomephone: " + home_phone
                + "\ntype: " + type
            + "\n}";
    }
}
