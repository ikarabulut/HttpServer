package com.ikarabulut.server;

import com.ikarabulut.handlers.ClientHandler;
import com.ikarabulut.io.ServerIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server {
    private int port;
    private SocketFactory socketFactory;
    private ServerIO serverIO;
    private int THREAD_COUNT = 5;

    public Server(int port, SocketFactory socketFactory, ServerIO serverIO) {
        this.port = port;
        this.socketFactory = socketFactory;
        this.serverIO = serverIO;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = socketFactory.createServerSocket(port);

        ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);

        serverIsListening(serverSocket, pool);
    }

    public int getPort() {
        return this.port;
    }

    private void serverIsListening(ServerSocket serverSocket, ExecutorService pool) {
        while (serverSocket.isBound()) {
            try {
                Socket clientSocket = socketFactory.createClientSocket(serverSocket);
                pool.execute(new ClientHandler(clientSocket, serverIO));
            } catch (IOException ex) {
                ex.printStackTrace();
                System.err.println("Unable to bind Client Socket");
            }
        }
    }


}
