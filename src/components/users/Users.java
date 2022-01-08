package components.users;

import db.databaseHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import server.request.Request;
import server.Response;
import server.http.ContentType;
import server.http.HttpStatus;

import java.sql.SQLException;

public class Users implements UserInterface{
    //public databaseHandler dbHandler;
    public UserHandler userHandler;

    public Users(){
        this.userHandler = new UserHandler();
    }

    //------------ CREATE USER
    public Response createUser(Request request) throws ParseException, SQLException {
        //for the response
        String return_status;

        //for the user
        String username;
        String password;

        //create json object
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(request.getBody());

        //create strings
        username = json.get("Username").toString();
        password = json.get("Password").toString();


        //call function to connect to database
        return_status = this.userHandler.register_User(username, password);
        if(return_status.equals("201")){
            //System.out.println("Your user has been created");
            return new Response(HttpStatus.CREATED, ContentType.JSON, HttpStatus.CREATED.message + " \"Your user has been created\"");
        }
        if(return_status.equals("400")){
            //System.out.println("Your user already exists");
            return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, HttpStatus.BAD_REQUEST.message + "\"Your user already exists\"");
        }

        //check what the return status is and create a HttpStatus Code + Message
        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, HttpStatus.INTERNAL_SERVER_ERROR.message);
    }

    @Override
    public Response editUser(Request request) throws ParseException, SQLException {
        return null;
    }

    @Override
    public Response showUserData(Request request) throws ParseException, SQLException {
        return null;
    }

}
