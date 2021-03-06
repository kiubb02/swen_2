package components.packages;

import components.cards.cardsImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import server.request.Request;
import server.Response;
import server.http.ContentType;
import server.http.HttpStatus;

import java.io.IOException;
import java.sql.SQLException;

public class PackagesImpl implements Package {
    //public databaseHandler dbHandler;
    public PackageHandlerImpl packageHandlerImpl;
    public cardsImpl Card;

    public PackagesImpl(){
        this.packageHandlerImpl = new PackageHandlerImpl();
        this.Card = new cardsImpl();
    }

    // ---- CREATE PACKAGES
    @Override
    public Response createPackage(Request request) throws ParseException, IOException, SQLException {

            //to match the response
            String return_status = " ";

            //package attributes
            String id = "";
            String name = "";
            String damage = "";
            String desc = "";


            //iterate through it and create json objects
            JSONParser parser = new JSONParser();
            JSONArray json = (JSONArray) parser.parse(request.getBody());

            //create a Package
            return_status = this.packageHandlerImpl.createPackage();
            if(return_status.equals("400")){
                //System.out.println("Package couldn't have been created");
                return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, HttpStatus.BAD_REQUEST.message + " \"Package couldn't have been created\"");
            }

            System.out.println(json.size());

           for (int i = 0; i < json.size(); i++) {
                JSONObject object = (JSONObject) json.get(i);
                id = object.get("Id").toString();
                name = object.get("Name").toString();
                damage = object.get("Damage").toString();
                if(object.containsKey("Description")) desc = object.get("Description").toString();

                //call function to insert packages to database
                return_status = this.Card.createCard(id, name, damage, desc);
                if(return_status.equals("400")){
                    //System.out.println("This Card already exists");
                    return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, HttpStatus.BAD_REQUEST.message + " \"This Card already exists\"");
                }
            }


            return new Response(HttpStatus.CREATED, ContentType.JSON, HttpStatus.CREATED.message + " \"Your Package has been created\"");

    }
}
