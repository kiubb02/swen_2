package components.tradings;

import components.users.UserHandlerImpl;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class TradingHandlerImplTest {

    public TradingHandlerImpl tradingHandlerImpl = new TradingHandlerImpl();

    // {\"Id\": \"6cd85277-4590-49d4-b0cf-ba0a921faad0\", \"CardToTrade\": \"1cb6ab86-bdb2-47e5-b6e4-68c5ab389334\", \"Type\": \"monster\", \"MinimumDamage\": 15}
    String id = "6cd85277-4590-49d4-b0cf-ba0a921faad0";
    String card = "1cb6ab86-bdb2-47e5-b6e4-68c5ab389334";
    String type = "monster";
    int dmg = 15;
    String username = "kiukiu";

    @Test
    void checkTrades() throws SQLException {
        //there should be none
        assertEquals("404", tradingHandlerImpl.checkTrades());
    }

    @Test
    void createTrade() throws SQLException {
        assertEquals("201", tradingHandlerImpl.createTrade(id, card, type, dmg, username));
    }

    @Test
    void checkTradeCreator() {
        assertEquals("kiukiu", tradingHandlerImpl.checkTradeCreator(id));
    }

    @Test
    void getCardToChange() {
        assertEquals(card, tradingHandlerImpl.getCardToChange(id));
    }

    @Test
    void validateOwnership() throws SQLException {
        assertFalse(tradingHandlerImpl.validateOwnership(username, card));
    }

    @Test
    void removeTrade() throws SQLException {
        assertTrue(tradingHandlerImpl.removeTrade(id));
    }

}