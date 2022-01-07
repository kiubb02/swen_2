package components.packages;

import db.databaseInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PackageHandler implements PackageHandlerInterface{


    @Override
    public String createPackage() {
        String message = "201";

        try {
            Connection con = databaseInterface.getConnection(); //connect to the database
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("INSERT INTO packages(price) VALUES (?);");

            stmt.setInt(1, 5);

            stmt.execute();
            stmt.close();
            con.close();

        } catch(SQLException e){
            System.out.println(e);
            message = "400";
        }

        return message;
    }

    @Override
    public String openPackage() {
        return null;
    }
}
