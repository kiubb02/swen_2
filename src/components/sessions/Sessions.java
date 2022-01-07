package components.sessions;

import components.users.UserHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import server.Response;
import server.http.ContentType;
import server.http.HttpStatus;
import server.request.Request;

import java.sql.SQLException;

public class Sessions implements SessionsInterface{

    public SessionsHandler sessionsHandler = new SessionsHandler();

    @Override
    //------LOGIN USER
    public Response loginUser(Request request) throws ParseException, SQLException {
        //for the response
        String return_status = " ";

        //for the user
        String username = " ";
        String password = " ";

        //create json object
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(request.getBody());

        //create strings
        username = json.get("Username").toString();
        password = json.get("Password").toString();

        return_status = this.sessionsHandler.login_User(username, password);
        switch (return_status) {
            case "404" -> {
                //System.out.println("User not found try again");
                return new Response(HttpStatus.NOT_FOUND, ContentType.JSON, HttpStatus.NOT_FOUND.message + " \"User not found try again\"");
            }
            case "200" -> {
                //System.out.println("Login was successful");
                return new Response(HttpStatus.OK, ContentType.JSON, HttpStatus.OK.message + " \"Login was successful\"");
            }
            case "400" -> {
                //System.out.println("Something went wrong");
                return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, HttpStatus.BAD_REQUEST.message + " \"Something went wrong\"");
            }
        }

        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, HttpStatus.INTERNAL_SERVER_ERROR.message);
    }
}
