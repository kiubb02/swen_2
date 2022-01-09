package components.deck;

import components.RequestHandlerInterface;
import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.io.IOException;
import java.sql.SQLException;

public class DeckReq implements RequestHandlerInterface {

    private final Deck deck = new Deck();

    @Override
    public Response handleRequest(Request request) throws ParseException, IOException, SQLException {
        Response res = null;

        switch(request.getMethod()){
            case "GET" -> res = this.deck.showDecks(request);
            case "PUT" -> res = this.deck.createDeck(request);
        }

        return res;
    }
}
