package components.deck;

import org.codehaus.jackson.*;
import org.codehaus.jackson.map.ObjectMapper;


import org.json.simple.parser.ParseException;
import server.Response;
import server.http.ContentType;
import server.http.HttpStatus;
import server.request.Request;

import java.io.IOException;

public class DeckImpl implements Deck {

    private final DeckHandlerImpl deckHandlerImpl = new DeckHandlerImpl();

    @Override
    public Response showDecks(Request request) {

        String username = request.getUsername(); //get logged in user
        String message = "";


        if(request.getPathname().contains("plain")){
            System.out.println(" I am here ");
            message = this.deckHandlerImpl.getCardsofDeckPlain(username);
        } else {
            message = this.deckHandlerImpl.getCardsofDeck(username);
        }
        if(message.contains("200")) return new Response(HttpStatus.OK, ContentType.JSON, HttpStatus.OK.message + " \" " + message + " \"");
        if(message.contains("404")) return new Response(HttpStatus.NOT_FOUND, ContentType.JSON, HttpStatus.NOT_FOUND.message + " \" Your DeckImpl is empty \"");

        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, HttpStatus.BAD_REQUEST.message);
    }

    @Override
    public Response createDeck(Request request) throws ParseException, IOException {


        String message = "";


        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(request.getBody());

        if(node.size() != 4) return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, HttpStatus.BAD_REQUEST.message);


        //go through them and update
        for (int i = 0; i < node.size(); i++) {
            message = this.deckHandlerImpl.updateDeck(request.getUsername(), node.get(i).getValueAsText());
        }


        if(message.contains("200")) return new Response(HttpStatus.OK, ContentType.JSON, HttpStatus.OK.message + " \" " + message + " \"");
        if(message.contains("404")) return new Response(HttpStatus.OK, ContentType.JSON, HttpStatus.OK.message + " \" Your DeckImpl is empty \"");

        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, HttpStatus.INTERNAL_SERVER_ERROR.message);
    }
}
