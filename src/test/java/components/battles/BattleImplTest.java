package components.battles;

import components.tradings.TradingHandlerImpl;
import components.utils.Card;
import org.junit.jupiter.api.Test;
import server.Response;
import server.http.ContentType;
import server.http.HttpStatus;
import server.request.Request;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BattleImplTest {

    public BattleImpl battleImpl = new BattleImpl();

    Card card = new Card("67f9048f-99b8-4ae4-b866-d8008d00c53d", "10.0", "Water", "Monster", "WaterGoblin");
    Card card2 = new Card("67f9048f-99b8-4ae4-b866-d8008d00c53d", "10.0", "Water", "Monster", "WaterGoblin");
    Request req = new Request();

    @Test
    void start() throws SQLException {
        req.setUsername("kiukiu");
        Response res = new Response(HttpStatus.NOT_FOUND, ContentType.JSON, HttpStatus.NOT_FOUND.message + " \" No Battles Found.\"");
        assertEquals(res.get(), battleImpl.start(req).get());
    }

    @Test
    void calculateDMG() {
        assertEquals("Draw", battleImpl.calculateDMG(card, card2));
    }

    @Test
    void calculateELO() {
        assertEquals(140,battleImpl.calculateELO(100,100,1));
    }
}