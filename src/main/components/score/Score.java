package components.score;

import org.json.simple.parser.ParseException;
import server.Response;
import server.request.Request;

import java.io.IOException;
import java.sql.SQLException;

public interface Score {
    public Response userScores(String username) throws ParseException, IOException, SQLException;
}
