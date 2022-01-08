package components.tradings;

import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.sql.SQLException;

public interface TradingsInterface {
    public Response checkTradings(Request request) throws ParseException, SQLException;
    public Response stageTrading(Request request) throws ParseException, SQLException;
    public Response deleteTrading(Request request) throws ParseException, SQLException;
}
