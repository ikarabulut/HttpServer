package com.ikarabulut.server;

import com.ikarabulut.handlers.ClientHandler;
import com.ikarabulut.io.ServerIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Server {
    private int port;
    private ServerFactory serverFactory;
    private ServerIO serverIO;

    public Server(int port, ServerFactory serverFactory, ServerIO serverIO) {
        this.port = port;
        this.serverFactory = serverFactory;
        this.serverIO = serverIO;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = serverFactory.createServerSocket(port);

        int corePoolSize = 5;
        int maximumPoolSize = 5;
        long keepALiveTime = 60;
        TimeUnit keepAliveTimeunit = SECONDS;
        BlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>();
        AbstractExecutorService threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepALiveTime, keepAliveTimeunit, linkedBlockingQueue);

        serverIsListening(serverSocket, threadPool);

    }

    public int getPort() {
        return this.port;
    }

    private void serverIsListening(ServerSocket serverSocket, AbstractExecutorService threadPool) {
        while (serverSocket.isBound()) {
            try {
                Socket clientSocket = serverFactory.createClientSocket(serverSocket);
                threadPool.execute(new ClientHandler(clientSocket, serverIO));
            } catch (IOException ex) {
                ex.printStackTrace();
                System.err.println("Unable to bind Client Socket");
            }
        }
    }

}
