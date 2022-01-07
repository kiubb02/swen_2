package components.sessions;

import db.databaseInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionsHandler implements SessionsHandlerInterface{
    @Override
    public String login_User(String username, String password) throws SQLException {
        String message = "200"; //its OK at first

        try{
            Connection con = databaseInterface.getConnection(); //connect to the database
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("SELECT username, password FROM users WHERE username = ? AND password = ?;");
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet res = stmt.executeQuery();

            //check if res is done
            if(!res.isBeforeFirst()){
                return "404";
            }

            stmt.close();
            con.close();
        } catch (SQLException e) {
            message = "400";
        }


        return message;
    }
}
