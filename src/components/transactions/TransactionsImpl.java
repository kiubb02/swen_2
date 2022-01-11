package components.transactions;

import db.databaseInterface;
import org.json.simple.parser.ParseException;
import server.Response;
import server.http.ContentType;
import server.http.HttpStatus;
import server.request.Request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionsImpl implements Transactions {

    private final TransactionHandlerImpl transHandler = new TransactionHandlerImpl();

    @Override
    public Response aquirePackage(Request request) throws ParseException, SQLException {
        String message = "";

        if(this.checkMoney(request.getUsername())){
            //has enough money
            message = this.transHandler.buy_package(request.getUsername());
        }
        System.out.println(message);
        //else bad request
        if(message.equals("500")) {
            return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, HttpStatus.BAD_REQUEST.message + " \" You dont have enough Money.\"");
        }
        if(message.equals("200")){
            return new Response(HttpStatus.OK, ContentType.JSON, HttpStatus.OK.message + " \" Package aquired.\"");
        }

        return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, HttpStatus.BAD_REQUEST.message + " \" Something went wrong.\"");
    }

    @Override
    public Boolean checkMoney(String username) {

        int coins = 0;

        try{
            Connection con = databaseInterface.getConnection(); //connect to the database
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("SELECT coins FROM users WHERE username = ?;");
            stmt.setString(1, username);

            ResultSet res = stmt.executeQuery();

            //get the coins and check if they are bigger than 5
            while(res.next()){
                coins = res.getInt("coins");
            }

            if(coins < 5) return false;

            stmt.close();
            con.close();
        } catch (SQLException e) {
            return false;
        }

        return true;

    }
}
