package server.Authorization;

import server.request.Request;

import java.io.IOException;
import java.sql.SQLException;

public interface AuthorizationInterface {
    boolean checkToken(String username) throws SQLException;
    boolean checkUser(Request request) throws SQLException, IOException;
}
