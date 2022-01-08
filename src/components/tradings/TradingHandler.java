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
    public String createTrade(String id, String card, String type, int dmg) throws SQLException {

        String message = "201";

        try {
            Connection con = databaseInterface.getConnection(); //connect to the database
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("INSERT INTO tradings(tradingID,CardToTrade,Type,MinimumDamage) VALUES (?,?,?,?);");

            stmt.setString(1, id);
            stmt.setString(2, card);
            stmt.setString(3, type);
            stmt.setInt(4, dmg);


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
    public boolean validateOwnership(String username) throws SQLException {

        try {
            Connection con = databaseInterface.getConnection(); //connect to the database
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("""
                    SELECT *
                    FROM cards
                    WHERE "user" = ?
                    """);

            stmt.setString(1, username);

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
}
