package components.battles;

import components.utils.Card;
import components.utils.CardType;
import server.Response;
import server.request.Request;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Battle {
    public Response start(Request request) throws SQLException;
    void battle(String user, String opponent);
    void checkSpeciality(Card first, Card second);
    String calculateDMG(Card first, Card second);
    int calculateELO(int eloUser, int eloOpp, double  a);
}
