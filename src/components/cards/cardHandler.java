package components.cards;

import db.databaseInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class cardHandler implements cardHandlerInterface {
    @Override
    public String create_Card(String id, String name, String damage) throws SQLException {
        String message = "201";
        int id_p = 0;

        //get the last package
        try {
            Connection con = databaseInterface.getConnection();
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("SELECT id FROM packages ORDER BY id DESC LIMIT 1;");

            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                id_p = res.getInt("id");
                System.out.println(id_p);
            }


            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }


        try {
            Connection con = databaseInterface.getConnection(); //connect to the database
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("INSERT INTO cards(id, name, damage, id_p) VALUES (?,?,?, ?);");

            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, damage);
            stmt.setInt(4, id_p);

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
}
