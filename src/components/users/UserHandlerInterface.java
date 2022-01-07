package components.users;

import java.sql.SQLException;

public interface UserHandlerInterface {
    public String register_User(String username, String password) throws SQLException;
}
