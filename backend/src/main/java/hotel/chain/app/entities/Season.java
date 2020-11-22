package hotel.chain.app.entities;

import java.sql.Date;

public class Season {

    public int id;
    public String season_name;
    public float priceFactor;
    public Date starts;
    public Date ends;

    public Season(int id, String season_name, Date starts, Date ends, float priceFactor){
        this.id = id;
        this.season_name = season_name;
        this.priceFactor = priceFactor;
        this.starts = starts;
        this.ends = ends;
    }

    public Season() {
        id = 0;
        season_name = "empty";
        priceFactor = 1;
        starts = new Date(new java.util.Date().getTime());
        ends = new Date(new java.util.Date().getTime());
    }

    @Override
    public String toString() {
        return season_name + "(" + id + ", " + priceFactor + "x ) [ " + starts + " - " + ends + " ]";
    }


}


