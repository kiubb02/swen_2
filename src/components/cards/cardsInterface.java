package components.cards;

import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.io.IOException;
import java.sql.SQLException;

public interface cardsInterface {
    public String createCard(String id,String name,String damage) throws ParseException, IOException, SQLException;
    public Response showCards(Request request) throws SQLException;
}
