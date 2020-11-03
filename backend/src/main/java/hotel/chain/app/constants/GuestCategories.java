package hotel.chain.app.constants;

public enum GuestCategories {
    NONE("None", 1),
    VIP("VIP", 2),
    GOVERNMENT("Government", 3),
    MILITARY("Military", 4);

    private String name;
    private int id;

    public String getName(){
        return name;
    }

    public int getId(){ return id; }

    GuestCategories(String name, int id){
        this.id = id;
        this.name = name;
    }
}
