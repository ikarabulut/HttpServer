package com.ikarabulut.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) throws IOException {
        this.serverSocket = serverSocket;
    }

    public Socket createClientSocket() throws IOException {
        return serverSocket.accept();
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

}
