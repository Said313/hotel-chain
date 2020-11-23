package hotel.chain.app.controllers.profile;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ScheduleSetRequest {
    private String request;
    private int userID;
    private Time mondayStart;
    private Time mondayEnd;
    private Time tuesdayStart;
    private Time tuesdayEnd;
    private Time wednesdayStart;
    private Time wednesdayEnd;
    private Time thursdayStart;
    private Time thursdayEnd;
    private Time fridayStart;
    private Time fridayEnd;
    private Time saturdayStart;
    private Time saturdayEnd;
    private Time sundayStart;
    private Time sundayEnd;
    private float payroll;

    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    public int getUserID() {
        return userID;
    }

    public Time getMondayStart() {
        return mondayStart;
    }

    public Time getMondayEnd() {
        return mondayEnd;
    }

    public Time getTuesdayStart() {
        return tuesdayStart;
    }

    public Time getTuesdayEnd() {
        return tuesdayEnd;
    }

    public Time getWednesdayStart() {
        return wednesdayStart;
    }

    public Time getWednesdayEnd() {
        return wednesdayEnd;
    }

    public Time getThursdayStart() {
        return thursdayStart;
    }

    public Time getThursdayEnd() {
        return thursdayEnd;
    }

    public Time getFridayStart() {
        return fridayStart;
    }

    public Time getFridayEnd() {
        return fridayEnd;
    }

    public Time getSaturdayStart() {
        return saturdayStart;
    }

    public Time getSaturdayEnd() {
        return saturdayEnd;
    }

    public Time getSundayStart() {
        return sundayStart;
    }

    public Time getSundayEnd() {
        return sundayEnd;
    }

    public float getPayroll() {
        return payroll;
    }

    public ScheduleSetRequest(String request){
        this.request = request; parse();
    }

    private void parse(){
        try {

            JSONObject json = new JSONObject(request);
            userID = json.getInt("userID");
            payroll = BigDecimal.valueOf(json.getDouble("payroll")).floatValue();
            mondayStart = new Time(sdf.parse(json.getString("mondayStart")).getTime());
            mondayEnd = new Time(sdf.parse(json.getString("mondayEnd")).getTime());
            tuesdayStart = new Time(sdf.parse(json.getString("tuesdayStart")).getTime());
            tuesdayEnd = new Time(sdf.parse(json.getString("tuesdayEnd")).getTime());
            wednesdayStart = new Time(sdf.parse(json.getString("wednesdayStart")).getTime());
            wednesdayEnd = new Time(sdf.parse(json.getString("wednesdayEnd")).getTime());
            thursdayStart = new Time(sdf.parse(json.getString("thursdayStart")).getTime());
            thursdayEnd = new Time(sdf.parse(json.getString("thursdayEnd")).getTime());
            fridayStart = new Time(sdf.parse(json.getString("fridayStart")).getTime());
            fridayEnd = new Time(sdf.parse(json.getString("fridayEnd")).getTime());
            saturdayStart = new Time(sdf.parse(json.getString("saturdayStart")).getTime());
            saturdayEnd = new Time(sdf.parse(json.getString("saturdayEnd")).getTime());
            sundayStart = new Time(sdf.parse(json.getString("sundayStart")).getTime());
            sundayEnd = new Time(sdf.parse(json.getString("sundayEnd")).getTime());

        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
    }
}
