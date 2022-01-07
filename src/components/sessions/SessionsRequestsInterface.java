package components.sessions;

import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.sql.SQLException;

public interface SessionsRequestsInterface {
    public Response handleRequest(Request request) throws SQLException, ParseException;
}
