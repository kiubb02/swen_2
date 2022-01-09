package components.sessions;

import components.RequestHandlerInterface;
import components.users.Users;
import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.sql.SQLException;

public class SessionsRequests implements RequestHandlerInterface {
    private final Sessions newSession;

    public SessionsRequests() {
        this.newSession = new Sessions();
    }

    @Override
    public Response handleRequest(Request request) throws SQLException, ParseException {

        Response res = this.newSession.loginUser(request);

        return res;
    }
}
