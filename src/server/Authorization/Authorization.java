package server.Authorization;

import java.sql.SQLException;

public class Authorization implements AuthorizationInterface{

    private final AuthorizationHandler authHandler = new AuthorizationHandler();

    @Override
    public boolean checkToken(String username) throws SQLException {
        //check if the username in the token EXISTS
        if(authHandler.userExist(username)){
            return true;
        }
        return false;
    }
}
