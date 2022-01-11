package components.deck;

import components.RequestHandlerInterface;
import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.io.IOException;
import java.sql.SQLException;

public class DeckReq implements RequestHandlerInterface {

    private final DeckImpl deckImpl = new DeckImpl();

    @Override
    public Response handleRequest(Request request) throws ParseException, IOException, SQLException {
        Response res = null;

        switch(request.getMethod()){
            case "GET" -> res = this.deckImpl.showDecks(request);
            case "PUT" -> res = this.deckImpl.createDeck(request);
        }

        return res;
    }
}
