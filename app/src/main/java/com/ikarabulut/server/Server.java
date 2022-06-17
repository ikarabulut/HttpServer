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
    private SocketFactory socketFactory;
    private ServerIO serverIO;
    private int corePoolSize;
    int maximumPoolSize;
    long keepALiveTime;
    TimeUnit keepAliveTimeunit = SECONDS;

    public Server(int port, SocketFactory socketFactory, ServerIO serverIO) {
        this.port = port;
        this.socketFactory = socketFactory;
        this.serverIO = serverIO;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = socketFactory.createServerSocket(port);
        loadEnvVariables();

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
                Socket clientSocket = socketFactory.createClientSocket(serverSocket);
                threadPool.execute(new ClientHandler(clientSocket, serverIO));
            } catch (IOException ex) {
                ex.printStackTrace();
                System.err.println("Unable to bind Client Socket");
            }
        }
    }

    private void loadEnvVariables() {
        corePoolSize = Integer.parseInt(System.getenv("COREPOOLSIZE"));
        maximumPoolSize = Integer.parseInt(System.getenv("MAXIMUMPOOLSIZE"));
        keepALiveTime = Long.parseLong(System.getenv("KEEPALIVETIME"));
    }

}
