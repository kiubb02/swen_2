package components.deck;

import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.io.IOException;
import java.sql.SQLException;

public interface DeckReqInterface {
    public Response handleRequest(Request request) throws ParseException, IOException, SQLException;
}
