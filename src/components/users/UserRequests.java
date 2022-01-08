package components.users;

import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.sql.SQLException;

public class UserRequests implements UserRequestsInterface{
    private final Users newUser;

    public UserRequests() {
        this.newUser = new Users();
    }

    @Override
    public Response handleRequest(Request request) throws SQLException, ParseException {
        return this.newUser.createUser(request);
    }
}
