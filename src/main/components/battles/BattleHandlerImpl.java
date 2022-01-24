package components.battles;

import components.utils.Card;
import components.utils.CardType;
import db.databaseInterface;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BattleHandlerImpl implements BattleHandler{

    @Override
    public String checkBattles(String user) {

        String opponent = "";

        try {
            Connection con = databaseInterface.getConnection();
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("""
                    SELECT username
                    FROM users
                    WHERE username!=? AND "Bio"=?
                    LIMIT 1;
                    """);

            stmt.setString(1, user);
            stmt.setString(2, "ready");

            ResultSet res = stmt.executeQuery();

            if(res.next()){
                opponent=res.getString("username");
                this.updateState(user, "in Battle");
                this.updateState(opponent, "in Battle");
            }else{
                return "404";
            }

            res.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "200 " + opponent; //battle has been found and done
    }

    @Override
    public void updateState(String user, String bio) {
        //update user bio to "in battle"
        try {
            Connection con = databaseInterface.getConnection();
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("""
                    UPDATE users
                    SET "Bio"=?
                    WHERE username=?
                    """);

            stmt.setString(1, bio);
            stmt.setString(2, user);

            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public ArrayList<Card> getDeck(String user) {
        ArrayList<Card> Deck = new ArrayList<>();

        try {
            Connection con = databaseInterface.getConnection();
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("""
                    SELECT *
                    FROM cards
                    WHERE "user" = ?
                    AND
                    deck = ?
                    """);

            stmt.setString(1, user);
            stmt.setBoolean(2, true);

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                String id = res.getString("id");
                String Damage = res.getString("damage");
                String Element = res.getString("Elements");
                String Type = res.getString("Type");
                String Name = res.getString("name");

                Deck.add(new Card(id, Damage, Element, Type, Name));
            }

            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return Deck;
    }

    @Override
    public int getElo(String user) {

        int elo = 0;

        try {
            Connection con = databaseInterface.getConnection();
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("""
                    SELECT elo
                    FROM users
                    WHERE username=?;
                    """);

            stmt.setString(1, user);

            ResultSet res = stmt.executeQuery();

            if(res.next()){
                elo=res.getInt("elo");
            }else{
                return elo;
            }

            res.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return elo;
    }

    @Override
    public void updateBattleStats(String user, int lost, int won, int elo) {
        try {
            Connection con = databaseInterface.getConnection();
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("""
                    UPDATE users
                    SET elo=?, won=?, lost=?
                    WHERE username=?
                    """);

            stmt.setInt(1, elo);
            stmt.setInt(2, won);
            stmt.setInt(3, lost);
            stmt.setString(4, user);

            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
