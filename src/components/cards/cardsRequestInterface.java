package components.cards;

import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.io.IOException;
import java.sql.SQLException;

public interface cardsRequestInterface {
    public Response handleRequest(Request request) throws SQLException, ParseException, IOException;
}
