package components.stats;

import server.Response;

import java.sql.SQLException;

public interface Stats {
    Response UserStats(String username) throws SQLException;
}
