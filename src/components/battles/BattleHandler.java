package components.battles;

import components.utils.Card;
import components.utils.CardType;

import java.util.ArrayList;

public interface BattleHandler {
    void getReady(String username);
    String checkBattles(String user);
    void updateState(String user, String bio);
    ArrayList<Card> getDeck(String user);
    int getElo(String user);
    void updateBattleStats(String user, int lost, int won, int elo);
}
