package hotel.chain.app.constants.authorization;

public enum GuestCategories {
    EMPTY("empty", 0),
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

    
    
    public static GuestCategories getByName(String name){
        GuestCategories res = GuestCategories.EMPTY;
        for (GuestCategories guestCategory : GuestCategories.values()){
            if (name.equals(guestCategory.getName())){
                res = guestCategory;
            }
        }
        return res;
    }

    public static GuestCategories getById(int id){
        GuestCategories res = GuestCategories.EMPTY;
        for (GuestCategories guestCategory : GuestCategories.values()){
            if (guestCategory.getId() == id){
                res = guestCategory;
            }
        }
        return res;
    }
}
