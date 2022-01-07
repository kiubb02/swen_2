package components.sessions;

import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.sql.SQLException;

public interface SessionsInterface {
    public Response loginUser(Request request) throws ParseException, SQLException;
}
