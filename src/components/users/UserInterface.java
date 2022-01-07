package components.users;

import org.json.simple.parser.ParseException;
import server.request.Request;
import server.Response;

import java.sql.SQLException;

public interface UserInterface {

    public Response createUser(Request request) throws ParseException, SQLException;

}
