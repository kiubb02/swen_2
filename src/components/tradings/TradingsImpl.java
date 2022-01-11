package components.tradings;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.parser.ParseException;
import server.Response;
import server.http.ContentType;
import server.http.HttpStatus;
import server.request.Request;

import java.io.IOException;
import java.sql.SQLException;

public class TradingsImpl implements Tradings {

    public final TradingHandlerImpl tdHandler = new TradingHandlerImpl();


    @Override
    public Response checkTradings(Request request) throws ParseException, SQLException {
        //return all available TradingsImpl
        String message = "";

        message = this.tdHandler.checkTrades();
        if(message.contains("200")) return new Response(HttpStatus.OK, ContentType.JSON, HttpStatus.OK.message + " " + message);
        if(message.contains("404")) return new Response(HttpStatus.NOT_FOUND, ContentType.JSON, HttpStatus.NOT_FOUND.message + " No TradingsImpl Found");


        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, HttpStatus.INTERNAL_SERVER_ERROR.message);
    }

    @Override
    public Response stageTrading(Request request) throws ParseException, SQLException, IOException {

        System.out.println(request.getBody());

        //return all available TradingsImpl
        String message = "";

        //create a trading OR try to trade with one of your cardsImpl
        String[] split = request.getPathname().split("/");
        //if split.size() == 3 ==> someone wants to trade
        if(split.length == 3){
            //check if user who requests trade and creator are the same
            if(this.tdHandler.checkTradeCreator(split[2]).equals(request.getUsername())) return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, HttpStatus.BAD_REQUEST.message + " Cannot trade with yourself");

            //get the request card given
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(request.getBody());

            System.out.println(node.getValueAsText());

            //check ownership
            if(this.tdHandler.validateOwnership(request.getUsername(), node.getValueAsText())) {
                System.out.println("hello");
                //start the trade
                message = this.tdHandler.tradeCards(request.getUsername(), node.getValueAsText(), split[2]);
            }


        } else {
            //get trading params
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(request.getBody());

            String id = node.get("Id").getValueAsText();
            System.out.println(id);

            String card = node.get("CardToTrade").getValueAsText();
            String type = node.get("Type").getValueAsText();
            int dmg = Integer.parseInt(node.get("MinimumDamage").getValueAsText());

            //check if user owns this card
            if (this.tdHandler.validateOwnership(request.getUsername(), card)) {
                //then trade can be created
                System.out.println("hi");
                message = this.tdHandler.createTrade(id, card, type, dmg, request.getUsername());
            }
        }



        if (message.contains("201")) return new Response(HttpStatus.CREATED, ContentType.JSON, HttpStatus.CREATED.message + " Trading-Deal has been staged");
        if (message.contains("400")) return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, HttpStatus.BAD_REQUEST.message + " Something didnt work out");

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
