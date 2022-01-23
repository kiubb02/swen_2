package components.score;

import components.RequestHandlerInterface;
import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.io.IOException;
import java.sql.SQLException;

public class ScoreRequests implements RequestHandlerInterface {

    private final ScoreImpl newScore = new ScoreImpl();

    @Override
    public Response handleRequest(Request request) throws SQLException, ParseException, IOException {
        Response res = null;

        res = this.newScore.userScores(request.getUsername());

        return res;
    }
}
