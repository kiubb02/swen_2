package com.company;

import server.server.Server;
import server.server.App;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        App serverApp = new App();

        Server serverTest = new Server(10001, serverApp);
        serverTest.start();

    }
}
