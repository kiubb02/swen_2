package components.users;

import org.json.simple.parser.ParseException;
import server.request.Request;
import server.Response;

import java.sql.SQLException;

public interface User {

    public Response createUser(Request request) throws ParseException, SQLException;
    public Response editUser(Request request) throws ParseException, SQLException;
    public Response showUserData(Request request) throws ParseException, SQLException;

}
