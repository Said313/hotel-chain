package hotel.chain.app.constants.authorization;

public enum Id_type {
    EMPTY("empty", 0),
    US_PASSPORT("US passport", 1),
    DRIVING_LICENSE("Driving license", 2),
    NOT_PROVIDED("Not provided", 3);

    private String name;
    private int id;

    Id_type(String name, int id){
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public static Id_type getByName(String name){
        Id_type res = Id_type.EMPTY;
        for (Id_type id_type : Id_type.values()){
            if (name.equals(id_type.getName())){
                res = id_type;
            }
        }
        return res;
    }

    public static Id_type getById(int id){
        Id_type res = Id_type.EMPTY;
        for (Id_type id_type : Id_type.values()){
            if (id_type.getId() == id){
                res = id_type;
            }
        }
        return res;
    }
}
