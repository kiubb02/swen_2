package components.users;

import server.request.Request;

import java.sql.SQLException;

public interface UserHandler {
    public String register_User(String username, String password) throws SQLException;
    public String show_User(String username) throws SQLException;
    public String edit_User(String username, String Name, String Bio, String Image) throws SQLException;
}
