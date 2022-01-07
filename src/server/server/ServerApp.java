package server.server;

import org.json.simple.parser.ParseException;
import server.request.Request;
import server.Response;

import java.io.IOException;
import java.sql.SQLException;

public interface ServerApp {
    Response handleRequest(Request request) throws ParseException, SQLException, IOException;
}
