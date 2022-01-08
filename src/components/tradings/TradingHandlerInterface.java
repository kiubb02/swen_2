package components.tradings;

import java.sql.SQLException;

public interface TradingHandlerInterface {
    public String checkTrades() throws SQLException;
    public String createTrade(String id, String card, String type, int dmg) throws SQLException;
    public boolean removeTrade(String id) throws SQLException;
    public boolean validateOwnership(String username) throws SQLException;
}
