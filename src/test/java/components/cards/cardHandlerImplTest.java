package components.cards;

import components.tradings.TradingHandlerImpl;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class cardHandlerImplTest {

    public cardHandlerImpl cardHandlerImpl = new cardHandlerImpl();

    //{\"Id\":\"845f0dc7-37d0-426e-994e-43fc3ac83c08\", \"Name\":\"WaterGoblin\", \"Damage\": 10.0}
    String id = "845f0dc7-37d0-426e-994e-43fc3ac83c08";
    String name = "WaterGoblin";
    String damage = "10";
    String desc = "A nice card";

    @Test
    void create_Card() throws SQLException {
        assertEquals("201", cardHandlerImpl.create_Card(id, name, damage, desc));
    }

    @Test
    void getUserCards() throws SQLException {
        assertEquals("404", cardHandlerImpl.getUserCards("kiukiu"));
    }
}