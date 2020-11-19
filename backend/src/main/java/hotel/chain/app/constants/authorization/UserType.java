package hotel.chain.app.constants.authorization;

public enum UserType {
    EMPTY("empty", 0),
    GUEST("guest", 1),
    DESK_CLERK("desk clerk", 2),
    MANAGER("manager", 3);

    private String name;
    private int id;

    UserType(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static UserType getById(int id){
        UserType res = EMPTY;

        for (UserType type : UserType.values()) {
            if (type.getId() == id) {
                res = type;
            }
        }
        return res;
    }

    public static UserType getByName(String name){
        UserType res = UserType.EMPTY;
        for (UserType userType : UserType.values()){
            if (name.equals(userType.getName())){
                res = userType;
            }
        }
        return res;
    }

    public String getName() {return name; }
    public int getId() {return id; }
}
