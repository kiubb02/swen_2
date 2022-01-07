package components.deck;

import server.Response;
import server.http.ContentType;
import server.http.HttpStatus;
import server.request.Request;

public class Deck implements DeckInterface{

    private final DeckHandler deckHandler = new DeckHandler();

    @Override
    public Response showDecks(Request request) {

        String username = request.getUsername(); //get logged in user
        String message = this.deckHandler.getCardsofDeck(username);
        if(message.contains("200")) return new Response(HttpStatus.OK, ContentType.JSON, HttpStatus.OK.message + " \" " + message + " \"");
        if(message.contains("404")) return new Response(HttpStatus.OK, ContentType.JSON, HttpStatus.OK.message + " \" Your Deck is empty \"");

        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, HttpStatus.BAD_REQUEST.message);
    }

    @Override
    public Response createDeck(Request request) {

        //first check if we have enough cards


        String username = request.getUsername(); //get logged in user
        String message = this.deckHandler.getCardsofDeck(username);
        if(message.contains("200")) return new Response(HttpStatus.OK, ContentType.JSON, HttpStatus.OK.message + " \" " + message + " \"");
        if(message.contains("404")) return new Response(HttpStatus.OK, ContentType.JSON, HttpStatus.OK.message + " \" Your Deck is empty \"");

        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, HttpStatus.BAD_REQUEST.message);
    }
}
