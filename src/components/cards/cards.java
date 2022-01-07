package components.cards;

import components.users.UserHandler;
import org.json.simple.parser.ParseException;
import server.Response;
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
}
