package server.request;

import org.json.simple.parser.ParseException;
import server.Response;
import server.http.ContentType;
import server.http.HttpStatus;
import server.request.Request;
import server.server.ServerApp;
import server.utils.RequestBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

public class RequestHandler implements Runnable{
    private Socket clientSocket;
    private ServerApp app;
    private PrintWriter output;
    private BufferedReader in;

    public RequestHandler(Socket clientSocket, ServerApp app) throws IOException {
        this.clientSocket = clientSocket;
        this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        this.output = new PrintWriter(this.clientSocket.getOutputStream(), true);
        this.app = app;
    }

    @Override
    public void run() {
        try {
            Response response;
            Request request = RequestBuilder.buildRequest(this.in);

            if (request.getPathname() == null || request.getUsername() == null) {
                response = new Response(
                        HttpStatus.BAD_REQUEST,
                        ContentType.JSON,
                        "[]"
                );

            } else {
                response = this.app.handleRequest(request);
            }
            output.write(response.get());
        } catch (IOException | ParseException | SQLException e) {
            System.err.println(Thread.currentThread().getName() + " Error: " + e.getMessage());
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
