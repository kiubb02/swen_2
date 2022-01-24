package components.deck;

import db.databaseInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeckHandlerImpl implements DeckHandler {
    @Override
    public String getCardsofDeck(String username) {
        String message = "200";

        String Name = "";
        String Dmg = "";
        String Element = "";
        String Type = "";

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
                Name = res.getString("name");
                Dmg = res.getString("damage");
                Element = res.getString("Elements");
                Type = res.getString("Type");

                message += "\n{\"Name\":\"" + Name + "\",\"Damage\":\"" + Dmg + "\",\"Element\":\"" + Element + "\",\"Type\":\"" + Type + "\"}";
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

    @Override
    public String getCardsofDeckPlain(String username) {
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

    @Override
    public boolean checkOwner(String username, String card_id) {
        try {
            Connection con = databaseInterface.getConnection(); //connect to the database
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("""
                    SELECT *
                    FROM cards
                    WHERE "user" = ?
                    AND
                    "id" = ?
                    """);

            stmt.setString(1, username);
            stmt.setString(2, card_id);

            ResultSet res = stmt.executeQuery();

            //check if empty
            //return 404
            if(!res.isBeforeFirst()){
                return false;
            }


            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

        //if user already exists change message

        return true;
    }

    @Override
    public String updateDeck(String username, String card_id) {
        String message = "200";

        //first check if user owns cardsImpl
        Boolean checked = this.checkOwner(username, card_id);

                if(checked) {
                    try {
                        Connection con = databaseInterface.getConnection(); //connect to the database
                        assert con != null;
                        //create prepared statement
                        PreparedStatement stmt = con.prepareStatement("""
                                UPDATE cards
                                SET deck = ?
                                WHERE "user" = ?
                                AND
                                "id" = ?
                                """);

                        stmt.setBoolean(1, true);
                        stmt.setString(2, username);
                        stmt.setString(3, card_id);

                        stmt.execute();


                        stmt.close();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println(e);
                        message = "500";
                    }
                } else {
                    message = "400";
                }

        //if user already exists change message

        return message;
    }


}
