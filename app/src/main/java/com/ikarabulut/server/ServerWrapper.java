package com.ikarabulut.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerWrapper {

    public ServerSocket createServerSocket(int port) {
        try {
            return new ServerSocket(port);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Socket createClientSocket(ServerSocket serverSocket) {
        try {
            return serverSocket.accept();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
