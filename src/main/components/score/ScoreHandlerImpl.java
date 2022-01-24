package components.score;

import db.databaseInterface;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScoreHandlerImpl implements ScoreHandler{

    @Override
    public String getScore(String username) {
        StringBuilder message = new StringBuilder("200");

        try {
            Connection con = databaseInterface.getConnection(); //connect to the database
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("""
              SELECT elo, username
              FROM users
              ORDER BY elo DESC;
            """);

            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                String elo = res.getString("elo");
                String user = res.getString("username");
                message.append("\n{\"Username\":\"").append(user).append("\",\"Elo\":\"").append(elo).append("\"}");
            }


            stmt.close();
            con.close();

        } catch(SQLException e){
            System.out.println(e);
            message = new StringBuilder("404"); //not found
        }

        return message.toString();
    }

}
