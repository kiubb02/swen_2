package components.cards;

import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.io.IOException;
import java.sql.SQLException;

public interface cards {
    public String createCard(String id,String name,String damage, String desc) throws ParseException, IOException, SQLException;
    public Response showCards(Request request) throws SQLException;
}
