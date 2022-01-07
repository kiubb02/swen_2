package components.users;

import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.sql.SQLException;

public interface UserRequestsInterface {

    public Response handleRequest(Request request) throws SQLException, ParseException;
}
