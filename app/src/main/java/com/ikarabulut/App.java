package com.ikarabulut;

import com.ikarabulut.server.Server;
import com.ikarabulut.server.SocketFactory;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        int PORT = 5000;
        SocketFactory socketFactory = new SocketFactory();

        Server server = new Server(PORT, socketFactory);
        System.out.println("You are listening on port: " + PORT);
        try {
            server.bind();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.print("Unable to connect server");
            System.exit(1);
        }

    }

}
