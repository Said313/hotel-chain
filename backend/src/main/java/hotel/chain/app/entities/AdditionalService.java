package hotel.chain.app.entities;

public class AdditionalService {
    private int serviceID;
    private String name;
    private float price;

    public AdditionalService(int serviceID, String name, float price) {
        this.serviceID = serviceID;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString(){
        return "AdditionalService { serviceID: " + serviceID + ", name: " + name + ", price: " + price + " }";
    }

    public int getServiceID() {return serviceID;}
    public String getName(){return name;}
    public float getPrice(){return price;}
}
