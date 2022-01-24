package components.stats;

import components.RequestHandlerInterface;
import components.cards.cardsImpl;
import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.io.IOException;
import java.sql.SQLException;

public class StatsRequests implements RequestHandlerInterface {
    private final StatsImpl Stats = new StatsImpl();

    @Override
    public Response handleRequest(Request request) throws SQLException, ParseException, IOException, InterruptedException {
        return Stats.UserStats(request.getUsername());
    }
}
