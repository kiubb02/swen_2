package components.tradings;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import server.Response;
import server.http.ContentType;
import server.http.HttpStatus;
import server.request.Request;

import java.sql.SQLException;

public class Tradings implements TradingsInterface{

    public final TradingHandler tdHandler = new TradingHandler();


    @Override
    public Response checkTradings(Request request) throws ParseException, SQLException {
        //return all available Tradings
        String message = "";

        message = this.tdHandler.checkTrades();
        if(message.contains("200")) return new Response(HttpStatus.OK, ContentType.JSON, HttpStatus.OK.message + " " + message);
        if(message.contains("404")) return new Response(HttpStatus.NOT_FOUND, ContentType.JSON, HttpStatus.NOT_FOUND.message + " No Tradings Found");


        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, HttpStatus.INTERNAL_SERVER_ERROR.message);
    }

    @Override
    public Response stageTrading(Request request) throws ParseException, SQLException {

        System.out.println("hi");

        //return all available Tradings
        String message = "";

        //get trading params
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(request.getBody());

        String id = json.get("Id").toString();
        String card = json.get("CardToTrade").toString();
        String type = json.get("Type").toString();
        int dmg = Integer.parseInt(json.get("MinimumDamage").toString());

        //check if user owns this card
        if(this.tdHandler.validateOwnership(request.getUsername())) {

            //then trade can be created
            message = this.tdHandler.createTrade(id, card, type, dmg);
            if (message.contains("201")) return new Response(HttpStatus.CREATED, ContentType.JSON, HttpStatus.CREATED.message + " Trading-Deal has been created");
            if (message.contains("400")) return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, HttpStatus.BAD_REQUEST.message + " Something didn't work out");
        }

        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, HttpStatus.INTERNAL_SERVER_ERROR.message);
    }

    @Override
    public Response deleteTrading(Request request) throws ParseException, SQLException {
        String message = "";

        String[] split = request.getPathname().split("/");
        String trading = split[2];
        System.out.println(trading);

        if(this.tdHandler.removeTrade(trading)){
            return new Response(HttpStatus.OK, ContentType.JSON, HttpStatus.OK.message + " Trading-Deal has been removed");
        }


        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, HttpStatus.INTERNAL_SERVER_ERROR.message);
    }
}
