package hotel.chain.app.controllers.authorization;

import hotel.chain.app.roles.Guest;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class LoginRequest {
    private String request;
    private String login;
    private String password;

    public LoginRequest(String request){
        this.request = request;
        parseRequest();
    }

    public void parseRequest(){
        try {
            JSONObject json = new JSONObject(request);
            login = json.getString("login");
            password = json.getString("password");
        } catch (JSONException e) {
            e.printStackTrace();
            login = "noLogin!";
            password = "noPassword!";
        }
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
