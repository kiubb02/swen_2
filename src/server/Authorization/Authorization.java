package server.Authorization;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import server.request.Request;

import java.io.IOException;
import java.sql.SQLException;

public class Authorization implements AuthorizationInterface{

    private final AuthorizationHandler authHandler = new AuthorizationHandler();

    @Override
    public boolean checkUser(Request request) throws SQLException, IOException {

        boolean authorized = true;
        String[] req = request.getPathname().split("/");



        //authorization set?
        if(!request.getUsername().equals("")){
            if(this.checkToken(request.getUsername())){

                //if a username is present in the jsonObject OR we have a users/<username> then check as well
                if((req.length == 3) && req[1].equals("users")){
                    if(!req[2].equals(request.getUsername())) return false;
                }

                //authorized is true

            }else{
                authorized = false;
            }
        }

        return authorized;
    }


    @Override
    public boolean checkToken(String username) throws SQLException {
        //check if the username in the token EXISTS
        if(authHandler.userExist(username)){
            return true;
        }
        return false;
    }
}
