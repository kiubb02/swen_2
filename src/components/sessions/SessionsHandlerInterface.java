package components.sessions;

import java.sql.SQLException;

public interface SessionsHandlerInterface {
    public String login_User(String username, String password) throws SQLException;
}
