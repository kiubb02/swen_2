package components.cards;

import components.RequestHandlerInterface;
import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.io.IOException;
import java.sql.SQLException;

public class cardsRequest implements RequestHandlerInterface {
    private final cardsImpl Card = new cardsImpl();

    @Override
    public Response handleRequest(Request request) throws SQLException, ParseException, IOException {
        Response res = this.Card.showCards(request);
        return res;
    }
}
