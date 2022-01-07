package components.cards;

import java.sql.SQLException;

public interface cardHandlerInterface {
    public String create_Card(String id, String name, String damage) throws SQLException;
    public String getUserCards(String username) throws SQLException;
}
