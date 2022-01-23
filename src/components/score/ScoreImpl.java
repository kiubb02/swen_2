package components.score;

import components.cards.cardsImpl;
import components.packages.PackageHandlerImpl;
import org.json.simple.parser.ParseException;
import server.Response;
import server.http.ContentType;
import server.http.HttpStatus;
import server.request.Request;

import java.io.IOException;
import java.sql.SQLException;

public class ScoreImpl implements Score{

    public ScoreHandlerImpl scoreHandler;

    public ScoreImpl(){
        this.scoreHandler = new ScoreHandlerImpl();
    }

    //-------- GET USER SCORES
    @Override
    public Response userScores(String username) throws ParseException, IOException, SQLException {
        String return_status = this.scoreHandler.getScore(username);

        if(return_status.contains("200")){
            return new Response(HttpStatus.OK, ContentType.JSON, HttpStatus.OK.message + return_status);
        }

        return new Response(HttpStatus.NOT_FOUND, ContentType.JSON, HttpStatus.NOT_FOUND.message + " \"You have no scores yet\"");

    }
}
