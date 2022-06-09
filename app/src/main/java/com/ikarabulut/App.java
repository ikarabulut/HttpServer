package com.ikarabulut;

import com.ikarabulut.io.ServerIO;
import com.ikarabulut.server.Server;
import com.ikarabulut.server.ServerWrapper;

public class App {
    public static void main(String[] args) {
        int PORT = 5000;
        ServerWrapper serverWrapper = new ServerWrapper();
        ServerIO serverIO = new ServerIO();

        Server server = new Server(PORT, serverWrapper, serverIO);
        System.out.println("You are listening on port: " + PORT);
        server.start();

    }

}
