package components.deck;

import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

public interface DeckInterface {
    public Response showDecks(Request request);
    public Response createDeck(Request request) throws ParseException;
}
