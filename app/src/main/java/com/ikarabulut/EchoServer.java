package com.ikarabulut;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private ServerSocket serverSocket;

    public EchoServer(ServerSocket serverSocket) throws IOException {
        this.serverSocket = serverSocket;
    }

    public Socket createClientSocket() throws IOException {
        return serverSocket.accept();
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

}
