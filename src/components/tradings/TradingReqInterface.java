package components.tradings;

import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.sql.SQLException;

public interface TradingReqInterface {
    public Response handleRequest(Request request) throws SQLException, ParseException;
}
