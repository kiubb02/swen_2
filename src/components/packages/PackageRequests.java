package components.packages;

import components.RequestHandlerInterface;
import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.io.IOException;
import java.sql.SQLException;

public class PackageRequests implements RequestHandlerInterface {
    private final Packages newPackage = new Packages();

    @Override
    public Response handleRequest(Request request) throws SQLException, ParseException, IOException {
        Response res = null;

        if(request.getUsername().equals("admin")){
            res = this.newPackage.createPackage(request);
        }

        return res;
    }
}
