package components.cards;

import components.users.UserHandler;
import org.json.simple.parser.ParseException;
import server.Response;
import server.http.ContentType;
import server.http.HttpStatus;
import server.request.Request;

import java.io.IOException;
import java.sql.SQLException;

public class cards implements cardsInterface {
    public cardHandler cardHandler = new cardHandler();

    @Override
    public String createCard(String id, String name, String damage) throws ParseException, IOException, SQLException {
        String res = this.cardHandler.create_Card(id, name, damage);
        return res;
    }

    @Override
    public Response showCards(Request request) throws SQLException {
        String message = this.cardHandler.getUserCards(request.getUsername());
        if(message.contains("200")) return new Response(HttpStatus.OK, ContentType.JSON, HttpStatus.OK.message + " \" " + message + " \"");

        if(message.equals("404"))  return new Response(HttpStatus.NOT_FOUND, ContentType.JSON, HttpStatus.NOT_FOUND.message + " \" No Cards could be found.\"");

        return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, HttpStatus.BAD_REQUEST.message + " \" Cards couldnt be shown or no cards available.\"");
    }
}
