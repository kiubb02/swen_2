package components.deck;

import org.codehaus.jackson.map.JsonNode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import server.Response;
import server.http.ContentType;
import server.http.HttpStatus;
import server.request.Request;

import java.util.ArrayList;

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
    public Response createDeck(Request request) throws ParseException {

        //first check if we have enough cards
        //iterate through it and create json objects
        //if(size() != 4) return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, HttpStatus.BAD_REQUEST.message);

        /*
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(request.getBody());

        //go through them and update
        for (int i = 0; i < json.size(); i++) {

        }
        */


        String username = request.getUsername(); //get logged in user
        String message = this.deckHandler.getCardsofDeck(username);
        if(message.contains("200")) return new Response(HttpStatus.OK, ContentType.JSON, HttpStatus.OK.message + " \" " + message + " \"");
        if(message.contains("404")) return new Response(HttpStatus.OK, ContentType.JSON, HttpStatus.OK.message + " \" Your Deck is empty \"");

        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, HttpStatus.INTERNAL_SERVER_ERROR.message);
    }
}
