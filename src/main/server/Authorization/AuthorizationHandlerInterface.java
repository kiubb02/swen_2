package server.Authorization;

import java.sql.SQLException;

public interface AuthorizationHandlerInterface {
    public boolean userExist(String username) throws SQLException;
}
