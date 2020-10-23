package hotel.chain.app;

public enum Id_type {

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
}
