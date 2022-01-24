package components.stats;

import java.sql.SQLException;

public interface StatsHandler {
    public String getStats(String username) throws SQLException;
}
