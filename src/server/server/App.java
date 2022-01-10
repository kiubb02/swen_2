package server.server;

import components.cards.cardsRequest;
import components.deck.DeckReq;
import components.packages.PackageRequests;
import components.sessions.SessionsRequests;
import components.tradings.TradingReq;
import components.transactions.TransactionReq;
import components.users.UserRequests;
import org.json.simple.parser.ParseException;
import server.Authorization.Authorization;
import server.http.ContentType;
import server.http.HttpStatus;

import server.request.Request;
import server.Response;

//Components
import components.users.Users;
import components.packages.Packages;

import java.io.IOException;
import java.sql.SQLException;

public class App implements ServerApp {

    private final UserRequests UserReq = new UserRequests();
    private final PackageRequests PackageReq = new PackageRequests();
    private final SessionsRequests SessionsReq = new SessionsRequests();
    private final TransactionReq transReq = new TransactionReq();
    private final cardsRequest cardReq = new cardsRequest();
    private final DeckReq deckReq = new DeckReq();
    private final TradingReq tradingReq = new TradingReq();
    private final Authorization auth = new Authorization();

    public App(){

    }

    @Override
    public Response handleRequest(Request request) throws ParseException, SQLException, IOException {
        Response res = null;
        boolean authorized = true;

        String[] req = request.getPathname().split("/");
        //part req to get main root

        //authorization set?
        if(!request.getUsername().equals("")){
            if(auth.checkToken(request.getUsername())){
                authorized = true;
            }else{
                authorized = false;
            }
        }

        if(authorized) { //if token is correct we can continue

            switch (req[1]) {
                case "users" -> res = this.UserReq.handleRequest(request);
                case "packages" -> res = this.PackageReq.handleRequest(request);
                case "sessions" -> res = this.SessionsReq.handleRequest(request);
                case "transactions" -> res = this.transReq.handleRequest(request);
                case "cards" -> res = this.cardReq.handleRequest(request);
                case "deck", "deck?format=plain" -> res = this.deckReq.handleRequest(request);
                case "stats" -> System.out.println("Hello Stats");
                case "score" -> System.out.println("Hello Score");
                case "tradings" -> res = this.tradingReq.handleRequest(request);
            }
        }

        System.out.println(res);

        return res;
    }

}
