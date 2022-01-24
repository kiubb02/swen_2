package components.cards;

import java.sql.SQLException;

public interface cardHandler {
    public String create_Card(String id, String name, String damage) throws SQLException;
    public String getUserCards(String username) throws SQLException;
}
