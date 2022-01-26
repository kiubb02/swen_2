package components.battles;

import components.tradings.TradingHandlerImpl;
import components.utils.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BattleHandlerImplTest {

    public BattleHandlerImpl battleHandlerImpl = new BattleHandlerImpl();

    @Test
    void checkBattles() {
        assertEquals("404", battleHandlerImpl.checkBattles("kiukiu"));
    }

    @Test
    void getDeck() {
        ArrayList<Card> empty = new ArrayList<Card>();
        assertArrayEquals(empty.toArray(), battleHandlerImpl.getDeck("kiukiu").toArray());
    }

    @Test
    void getElo() {
        assertEquals(100, battleHandlerImpl.getElo("kiukiu"));
    }
}