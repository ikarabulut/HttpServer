package com.ikarabulut;

import com.ikarabulut.io.ServerIO;
import com.ikarabulut.server.Server;
import com.ikarabulut.server.ServerFactory;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        int PORT = 5000;
        ServerFactory serverFactory = new ServerFactory();
        ServerIO serverIO = new ServerIO();

        Server server = new Server(PORT, serverFactory, serverIO);
        System.out.println("You are listening on port: " + PORT);
        try {
            server.start();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.print("Unable to connect server");
        }

    }

}
