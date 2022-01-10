package server.Authorization;

import db.databaseInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorizationHandler implements AuthorizationHandlerInterface{
    @Override
    public boolean userExist(String username) throws SQLException {

        try{
            Connection con = databaseInterface.getConnection(); //connect to the database
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM users WHERE username = ?;");
            stmt.setString(1, username);

            ResultSet res = stmt.executeQuery();

            //check if res is done
            if(!res.isBeforeFirst()){
                return false;
            }

            stmt.close();
            con.close();
        } catch (SQLException e) {
            return false;
        }


        return true;
    }
}
