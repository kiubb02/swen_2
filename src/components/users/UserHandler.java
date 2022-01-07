package components.users;

import db.databaseInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserHandler implements UserHandlerInterface{
    @Override
    public String register_User(String username, String password) throws SQLException {
        String message = "201";

        try {
            Connection con = databaseInterface.getConnection(); //connect to the database
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("INSERT INTO users(username, password) VALUES (?,?);");
            stmt.setString(1, username);
            stmt.setString(2, password);

            stmt.execute();
            stmt.close();
            con.close();
        } catch(SQLException e){
            message = "400";
        }

        //if user already exists change message

        return message;
    }


}
