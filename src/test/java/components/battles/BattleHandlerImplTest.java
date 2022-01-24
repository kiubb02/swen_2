package components.battles;

import components.tradings.TradingHandlerImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleHandlerImplTest {

    public BattleHandlerImpl battleHandlerImpl = new BattleHandlerImpl();

    @Test
    void checkBattles() {
        assertEquals("404", battleHandlerImpl.checkBattles("kiukiu"));
    }

    @Test
    void getDeck() {
        assertNull(battleHandlerImpl.getDeck("kiukiu"));
    }

    @Test
    void getElo() {
        assertEquals(0, battleHandlerImpl.getElo("kiukiu"));
    }
}