package components.deck;

import org.codehaus.jackson.*;
import org.codehaus.jackson.map.ObjectMapper;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import server.Response;
import server.http.ContentType;
import server.http.HttpStatus;
import server.request.Request;

import java.io.IOException;
import java.util.ArrayList;

public class Deck implements DeckInterface{

    private final DeckHandler deckHandler = new DeckHandler();

    @Override
    public Response showDecks(Request request) {

        String username = request.getUsername(); //get logged in user
        String message = "";

        if(request.getPathname().equals("")){
            message = this.deckHandler.getCardsofDeckPlain(username);
        } else {
            message = this.deckHandler.getCardsofDeck(username);
        }
        if(message.contains("200")) return new Response(HttpStatus.OK, ContentType.JSON, HttpStatus.OK.message + " \" " + message + " \"");
        if(message.contains("404")) return new Response(HttpStatus.NOT_FOUND, ContentType.JSON, HttpStatus.NOT_FOUND.message + " \" Your Deck is empty \"");

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
            message = this.deckHandler.updateDeck(request.getUsername(), node.get(i).getValueAsText());
        }


        if(message.contains("200")) return new Response(HttpStatus.OK, ContentType.JSON, HttpStatus.OK.message + " \" " + message + " \"");
        if(message.contains("404")) return new Response(HttpStatus.OK, ContentType.JSON, HttpStatus.OK.message + " \" Your Deck is empty \"");

        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, HttpStatus.INTERNAL_SERVER_ERROR.message);
    }
}
