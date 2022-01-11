package components.sessions;

import components.RequestHandlerInterface;
import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.sql.SQLException;

public class SessionsRequests implements RequestHandlerInterface {
    private final SessionsImpl newSession;

    public SessionsRequests() {
        this.newSession = new SessionsImpl();
    }

    @Override
    public Response handleRequest(Request request) throws SQLException, ParseException {

        Response res = this.newSession.loginUser(request);

        return res;
    }
}
