package server.server;

import components.cards.cardsRequest;
import components.packages.PackageRequests;
import components.sessions.SessionsRequests;
import components.transactions.TransactionReq;
import components.users.UserRequests;
import org.json.simple.parser.ParseException;
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

    private final Users newUser;
    private final Packages newPackage;

    private final UserRequests UserReq = new UserRequests();
    private final PackageRequests PackageReq = new PackageRequests();
    private final SessionsRequests SessionsReq = new SessionsRequests();
    private final TransactionReq transReq = new TransactionReq();
    private final cardsRequest cardReq = new cardsRequest();

    public App(){
        this.newUser = new Users();
        this.newPackage = new Packages();
    }

    @Override
    public Response handleRequest(Request request) throws ParseException, SQLException, IOException {
        Response res = null;

        switch (request.getPathname()) {
            case "/users" -> res = UserReq.handleRequest(request);
            case "/packages" -> res = PackageReq.handleRequest(request);
            case "/sessions" -> res = SessionsReq.handleRequest(request);
            case "/transactions/packages" -> res = transReq.handleRequest(request);
            case "/cards" -> res = cardReq.handleRequest(request);
            case "/deck" -> System.out.println("Hello Deck");
            case "/stats" -> System.out.println("Hello Stats");
            case "/score" -> System.out.println("Hello Score");
            case "/tradings" -> System.out.println("Hello Tradings");
        }

        return res;
    }

}
