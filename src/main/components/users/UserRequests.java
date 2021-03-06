package components.users;

import components.RequestHandlerInterface;
import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.sql.SQLException;

public class UserRequests implements RequestHandlerInterface {
    private final UserImpl newUser;

    public UserRequests() {
        this.newUser = new UserImpl();
    }

    @Override
    public Response handleRequest(Request request) throws SQLException, ParseException {
        Response res = null;

        switch(request.getMethod()){
            case "POST" -> res = this.newUser.createUser(request);
            case "PUT" -> res = this.newUser.editUser(request);
            case "GET" -> res = this.newUser.showUserData(request);
        }

        return res;
    }
}
