package components.tradings;

import db.databaseInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TradingHandler implements TradingHandlerInterface{
    @Override
    public String checkTrades() throws SQLException {
        String message = "200";

        String id = "";
        String CardToTrade = "";
        String Type = "";
        int dmg = 0;

        try {
            Connection con = databaseInterface.getConnection(); //connect to the database
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("""
                    SELECT *
                    FROM tradings
                    """);

            ResultSet res = stmt.executeQuery();

            //check if empty
            //return 404
            if(!res.isBeforeFirst()){
                return "404";
            }

            //else go through all the results and save
            while(res.next()){
                id = res.getString("tradingID");
                CardToTrade = res.getString("CardToTrade");
                Type = res.getString("Type");
                dmg = res.getInt("MinimumDamage");

            }

            message += "\n{\"ID\":\"" + id + "\",\"CardToTrade\":\"" + CardToTrade + "\",\"Type\":\"" + Type + "\",\"MinimumDamage\":\"" + dmg + "\"}";

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
    public String createTrade(String id, String card, String type, int dmg, String username) throws SQLException {

        String message = "201";

        System.out.println(type);

        try {
            Connection con = databaseInterface.getConnection(); //connect to the database
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("""
                    INSERT INTO tradings
                    ("tradingID","CardToTrade","Type","MinimumDamage","creator")
                    VALUES (?,?,?,?,?);
                    
                    """);

            stmt.setString(1, id);
            stmt.setString(2, card);
            stmt.setString(3, type);
            stmt.setInt(4, dmg);
            stmt.setString(5, username);


            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
            message = "400";
        }

        //if user already exists change message

        return message;
    }

    @Override
    public boolean removeTrade(String id) throws SQLException {
        //update the package here
        try {
            Connection con = databaseInterface.getConnection();
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("""
            
            DELETE FROM tradings
            WHERE "tradingID" = ?;
            
            """);

            stmt.setString(1, id);

            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    @Override
    public boolean validateOwnership(String username, String card) throws SQLException {

        try {
            Connection con = databaseInterface.getConnection(); //connect to the database
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("""
                    SELECT *
                    FROM cards
                    WHERE "user" = ? AND deck = ? AND "id" = ?
                    """);

            stmt.setString(1, username);
            stmt.setBoolean(2, false); //deck cards cannot be traded
            stmt.setString(3, card);

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
    public String tradeCards(String username, String cardName, String tradeNr) {
        String message = "201";

        //get creator to give them new card
        String creator = checkTradeCreator(tradeNr);
        //get the CardToTrade
        String CardToChange = getCardToChange(tradeNr);

        //trade the cards
        message = changeOwnership(creator, cardName);
        System.out.println(message);
        message = changeOwnership(username, CardToChange);
        System.out.println(message);

        //delete the trade
        if(message.equals("201")) message = deleteTrade(tradeNr);

        System.out.println(message);

        return message;
    }

    @Override
    public String checkTradeCreator(String tradeNr) {
        String user = "";

        try {
            Connection con = databaseInterface.getConnection(); //connect to the database
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("""
                    SELECT creator
                    FROM tradings
                    WHERE "tradingID" = ?
                    """);

            stmt.setString(1,tradeNr);

            ResultSet res = stmt.executeQuery();

            //check if empty
            //return 404
            if(!res.isBeforeFirst()){
                return "";
            }

            while(res.next()){
                user = res.getString("creator");
            }

            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
            return "";
        }

        //if user already exists change message

        return user;
    }



    @Override
    public String changeOwnership(String username, String cardname) {
        String message = "201";

        try {
            Connection con = databaseInterface.getConnection(); //connect to the database
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("""
                    UPDATE cards
                    SET "user" = ?
                    WHERE "id" = ?;
                    """);

            stmt.setString(1, username);
            stmt.setString(2, cardname); //deck cards cannot be traded

            stmt.execute();


            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
            message = "404";
        }
        return message;
    }

    @Override
    public String getCardToChange(String tradeNr) {
        String card = "";

        try {
            Connection con = databaseInterface.getConnection(); //connect to the database
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("""
                    SELECT "CardToTrade"
                    FROM tradings
                    WHERE "tradingID" = ?
                    """);

            stmt.setString(1,tradeNr);

            ResultSet res = stmt.executeQuery();

            //check if empty
            //return 404
            if(!res.isBeforeFirst()){
                return "";
            }

            while(res.next()){
                card = res.getString("CardToTrade");
            }

            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
            return "";
        }

        //if user already exists change message

        return card;
    }

    @Override
    public String deleteTrade(String tradeNr) {
        String message = "201";
        //update the package here
        try {
            Connection con = databaseInterface.getConnection();
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("""
                    DELETE FROM tradings
                    WHERE "tradingID" = ?;
                    """);
            stmt.setString(1, tradeNr);

            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
            message = "400";
        }

        return message;
    }
}
