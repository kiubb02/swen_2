package components.cards;

import org.json.simple.parser.ParseException;
import server.Response;
import server.http.ContentType;
import server.http.HttpStatus;
import server.request.Request;

import java.io.IOException;
import java.sql.SQLException;

public class cardsImpl implements cards {
    public cardHandlerImpl cardHandlerImpl = new cardHandlerImpl();

    @Override
    public String createCard(String id, String name, String damage) throws ParseException, IOException, SQLException {
        String res = this.cardHandlerImpl.create_Card(id, name, damage);
        return res;
    }

    @Override
    public Response showCards(Request request) throws SQLException {
        String message = this.cardHandlerImpl.getUserCards(request.getUsername());
        if(message.contains("200")) return new Response(HttpStatus.OK, ContentType.JSON, HttpStatus.OK.message + " \" " + message + " \"");

        if(message.equals("404"))  return new Response(HttpStatus.NOT_FOUND, ContentType.JSON, HttpStatus.NOT_FOUND.message + " \" No Cards could be found.\"");

        return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, HttpStatus.BAD_REQUEST.message + " \" Cards couldnt be shown or no cardsImpl available.\"");
    }
}
