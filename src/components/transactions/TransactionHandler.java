package components.transactions;

import db.databaseInterface;
import server.Response;
import server.http.ContentType;
import server.http.HttpStatus;
import server.request.Request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionHandler implements TransactionHandlerInterface{

    @Override
    public String buy_package(String username) throws SQLException {

        int id = this.getLatestPackage();
        //delete package


        if(id == 0) return "500";
        if(!this.deleteBought(id)) return "500";

        if(!this.aquireCards(username, id)) return "500";

        return "200";
    }

    @Override
    public int getLatestPackage() throws SQLException {
        int id = 0;

        try {
            Connection con = databaseInterface.getConnection();
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("SELECT id FROM packages ORDER BY id ASC LIMIT 1;");

            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                id = res.getInt("id");
            }


            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }

        return id;
    }

    @Override
    public boolean aquireCards(String username, int id) {
        try {
            Connection con = databaseInterface.getConnection();
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("UPDATE cards SET user = ? WHERE id_p = ?");
            stmt.setString(1, username);
            stmt.setInt(2, id);

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
    public boolean deleteBought(int id) {
        //update the package here
        try {
            Connection con = databaseInterface.getConnection();
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("DELETE FROM packages WHERE id = ?;");
            stmt.setInt(1, id);

            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

        return true;
    }
}
