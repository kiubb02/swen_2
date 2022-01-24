package components.tradings;
import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.io.IOException;
import java.sql.SQLException;

public interface Tradings {
    public Response checkTradings(Request request) throws ParseException, SQLException;
    public Response stageTrading(Request request) throws ParseException, SQLException, IOException;
    public Response deleteTrading(Request request) throws ParseException, SQLException;
}
