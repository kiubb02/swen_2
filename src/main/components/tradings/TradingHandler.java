package components.tradings;

import java.sql.SQLException;

public interface TradingHandler {
    String checkTrades() throws SQLException;
    String createTrade(String id, String card, String type, int dmg, String username) throws SQLException;
    boolean removeTrade(String id) throws SQLException;
    boolean validateOwnership(String username, String card) throws SQLException;

    String tradeCards(String username, String cardName, String tradeNr);
    String checkTradeCreator(String tradeNr);
    //String checkTradeCreatorByUsername(String tradeNr);

    String changeOwnership(String username, String cardname);
    String getCardToChange(String tradeNr);

    String deleteTrade(String tradeNr);
}
