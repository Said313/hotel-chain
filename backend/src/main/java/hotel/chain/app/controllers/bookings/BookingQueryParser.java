package hotel.chain.app.controllers.bookings;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingQueryParser
{
    private String request;
    private String destination;
    private Date start;
    private Date end;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public BookingQueryParser(String request){
        this.request = request;
        parse();
    }

    private void parse()
    {
        try
        {
            JSONObject json = new JSONObject(request);
            destination = json.getString("destination");

            String str_start = json.getString("start");
            String str_end = json.getString("end");

            start = sdf.parse(str_start);
            end = sdf.parse(str_end);
        }
        catch (JSONException | ParseException e)
        {
            e.printStackTrace();
            destination = "";
            start = new Date();                                             // today
            end = new Date(start.getTime() + (1000 * 60 * 60 * 24));        // tomorrow
        }
    }

    public String getDestination(){ return destination; }

    public Date getStart(){ return start; }

    public Date getEnd(){ return end; }
}
