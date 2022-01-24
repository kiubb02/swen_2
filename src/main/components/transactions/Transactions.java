package components.transactions;

import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.sql.SQLException;

public interface Transactions {
    public Response aquirePackage(Request request) throws ParseException, SQLException;
    public Boolean checkMoney(String username);
}
