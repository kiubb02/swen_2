package components.score;

import db.databaseInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ScoreHandlerImpl implements ScoreHandler{

    @Override
    public String getScore(String username) {
        String message = "200";

        try {
            Connection con = databaseInterface.getConnection(); //connect to the database
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM ;");

            stmt.setInt(1, 5);

            stmt.executeQuery();


            stmt.close();
            con.close();

        } catch(SQLException e){
            System.out.println(e);
            message = "404"; //not found
        }

        return message;
    }

}
