package server.server;

import server.request.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements ServerInterface{
    private ServerSocket serverSocket;
    private int port;
    private ServerApp app;
    private ExecutorService pool = Executors.newSingleThreadExecutor();

    public Server(int port, server.server.App app) {
        this.port = port;
        this.app = app;
    }

    public void start() throws IOException {
        System.out.println("Start http-server...");
        this.serverSocket = new ServerSocket(this.port);
        System.out.println("http-server running at: http://localhost:" + this.port);

        this.run();
    }

    private void run() {
        while (true) {
            try {
                Socket clientSocket = this.serverSocket.accept();
                RequestHandler requestHandler = new RequestHandler(clientSocket, this.app);
                // Thread Pool => asynchronous
                pool.execute(requestHandler);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
