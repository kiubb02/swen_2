package components.transactions;

import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.io.IOException;
import java.sql.SQLException;

public class TransactionReq implements TransactionReqInterface{
    private final Transactions trans = new Transactions();

    @Override
    public Response handleRequest(Request request) throws SQLException, ParseException, IOException {
        Response res = trans.aquirePackage(request);
        return res;
    }
}
