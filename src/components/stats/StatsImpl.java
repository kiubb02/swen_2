package components.stats;

import components.cards.cardHandlerImpl;
import server.Response;
import server.http.ContentType;
import server.http.HttpStatus;

import java.sql.SQLException;

public class StatsImpl implements Stats {

    public StatsHandlerImpl statsHandler = new StatsHandlerImpl();

    @Override
    public Response UserStats(String username) throws SQLException {
        String message = " ";

        message = this.statsHandler.getStats(username);

        if(message.contains("200")) return new Response(HttpStatus.OK, ContentType.JSON, HttpStatus.OK.message + " \" " + message + " \"");

        if(message.equals("404"))  return new Response(HttpStatus.NOT_FOUND, ContentType.JSON, HttpStatus.NOT_FOUND.message + " \" No Cards could be found.\"");

        return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, HttpStatus.BAD_REQUEST.message + " \" Cards couldnt be shown or no cardsImpl available.\"");
    }
}
