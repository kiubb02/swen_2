package components.transactions;

import server.Response;
import server.request.Request;

import java.sql.SQLException;

public interface TransactionHandler {
    public String buy_package(String username) throws SQLException;
    public int getLatestPackage() throws SQLException;
    public boolean aquireCards(String username, int id);
    public boolean deleteBought(int id);
    public int getCoins(String username) throws SQLException;
    public boolean updateCoins(int coins, String username) throws SQLException;
}
