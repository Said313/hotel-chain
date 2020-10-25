package hotel.chain.app.constants;

public enum GuestCategories {
    NONE("None"),
    VIP("VIP"),
    MILITARY("Military"),
    GOVERNMENT("Government");

    private String name;

    public String getName(){
        return name;
    }

    GuestCategories(String name){
        this.name = name;
    }
}
