package components.packages;

import org.json.simple.parser.ParseException;
import server.request.Request;
import server.Response;

import java.io.IOException;
import java.sql.SQLException;

public interface Package {

    public Response createPackage(Request request) throws ParseException, IOException, SQLException;
}
