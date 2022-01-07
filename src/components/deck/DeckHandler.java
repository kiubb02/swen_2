package components.deck;

import db.databaseInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeckHandler implements DeckHandlerInterface{
    @Override
    public String getCardsofDeck(String username) {
        String message = "200";

        try {
            Connection con = databaseInterface.getConnection(); //connect to the database
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("""
                    SELECT *
                    FROM cards
                    WHERE "user" = ?
                    AND
                    deck = ?
                    """);

            stmt.setString(1, username);
            stmt.setBoolean(2, true);

            ResultSet res = stmt.executeQuery();

            //check if empty
            //return 404
            if(!res.isBeforeFirst()){
                return "404";
            }

            //else go through all the results and save
            while(res.next()){
                message += res.getString("name").toString() + " " + res.getString("damage") + " \n";
            }

            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
            message = "500";
        }

        //if user already exists change message

        return message;
    }
}
