package components.tradings;

import components.RequestHandlerInterface;
import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.io.IOException;
import java.sql.SQLException;

public class TradingReq implements RequestHandlerInterface {

    private final Tradings newTrading = new Tradings();


    @Override
    public Response handleRequest(Request request) throws SQLException, ParseException, IOException {
        Response res = null;

        switch(request.getMethod()){
            case "GET" -> res = this.newTrading.checkTradings(request);
            case "POST" -> res = this.newTrading.stageTrading(request);
            case "DELETE" -> res = this.newTrading.deleteTrading(request);
        }

        return res;
    }
}
