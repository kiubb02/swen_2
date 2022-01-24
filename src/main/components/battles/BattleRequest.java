package components.battles;

import components.RequestHandlerInterface;
import components.cards.cardsImpl;
import org.json.simple.parser.ParseException;
import server.Response;
import server.http.ContentType;
import server.http.HttpStatus;
import server.request.Request;

import java.io.IOException;
import java.sql.SQLException;

public class BattleRequest implements RequestHandlerInterface {
    private final BattleImpl Battle = new BattleImpl();
    private final BattleHandlerImpl BattleH = new BattleHandlerImpl();

    @Override
    public Response handleRequest(Request request) throws SQLException, ParseException, IOException, InterruptedException {
        //set users ready
        this.BattleH.updateState(request.getUsername(), "ready");
        Thread.sleep(1000);
        Response res = this.Battle.start(request);
        return res;
    }
}
