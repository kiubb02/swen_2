package components.users;

import db.databaseInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserHandlerImpl implements UserHandler {
    @Override
    public String register_User(String username, String password) throws SQLException {
        String message = "201";

        try {
            Connection con = databaseInterface.getConnection(); //connect to the database
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("INSERT INTO users(username, password) VALUES (?,?);");
            stmt.setString(1, username);
            stmt.setString(2, password);

            stmt.execute();
            stmt.close();
            con.close();
        } catch(SQLException e){
            message = "400";
        }

        //if user already exists change message

        return message;
    }

    @Override
    public String show_User(String username) throws SQLException {
        String message = "200";
        String Username = " ", Name = " ", Bio = " ", image = " ";

        try {
            Connection con = databaseInterface.getConnection(); //connect to the database
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("""
                    SELECT *
                    FROM users
                    WHERE "username" = ?
                    """);

            stmt.setString(1, username);

            ResultSet res = stmt.executeQuery();

            //check if empty
            //return 404
            if(!res.isBeforeFirst()){
                return "404";
            }

            //else go through all the results and save
            while(res.next()){
                Username = res.getString("username");
                Name = res.getString("Name");
                Bio = res.getString("Bio");
                image = res.getString("image");
            }

            message += "{\"username\":\"" + Username + "\",\"Name\":\"" + Name + "\",\"Bio\":\"" + Bio + "\",\"image\":\"" + image + "\"}";

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
    public String edit_User(String username, String Name, String Bio, String Image) throws SQLException {
        String message = "200";

        try {
            Connection con = databaseInterface.getConnection(); //connect to the database
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("""
                    UPDATE users
                    SET "Name" = ?, "Bio" = ?, "image" = ?
                    WHERE "username" = ?
                    """);

            stmt.setString(1, Name);
            stmt.setString(2, Bio);
            stmt.setString(3, Image);
            stmt.setString(4, username);

            stmt.execute();


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
