package hotel.chain.app.controllers.profile;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class ScheduleGetRequest {
    private String request;
    private int userID;

    public ScheduleGetRequest (String request){
        this.request = request; parse();
    }

    private void parse (){
        try {
            JSONObject json = new JSONObject(request);
            userID = json.getInt("userID");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getUserID() {
        return userID;
    }
}
