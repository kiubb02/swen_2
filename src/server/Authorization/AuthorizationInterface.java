package server.Authorization;

import java.sql.SQLException;

public interface AuthorizationInterface {
    boolean checkToken(String username) throws SQLException;
}
