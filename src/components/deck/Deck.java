package components.deck;

import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.io.IOException;

public interface Deck {
    public Response showDecks(Request request);
    public Response createDeck(Request request) throws ParseException, IOException;
}
