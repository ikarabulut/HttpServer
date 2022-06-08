package com.ikarabulut.server;

import com.ikarabulut.ClientHandler;
import com.ikarabulut.ServerIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Server {
    private int port;
    private ServerWrapper serverWrapper;
    private ServerIO serverIO;

    public Server(int port, ServerWrapper serverWrapper, ServerIO serverIO) {
        this.port = port;
        this.serverWrapper = serverWrapper;
        this.serverIO = serverIO;
    }

    public void start() {
        ServerSocket serverSocket = serverWrapper.createServerSocket(port);

        int corePoolSize = 5;
        int maximumPoolSize = 5;
        long keepALiveTime = 60;
        TimeUnit keepAliveTimeunit = SECONDS;
        BlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepALiveTime, keepAliveTimeunit, linkedBlockingQueue);

        while (serverSocket.isBound()) {
            Socket clientSocket = serverWrapper.createClientSocket(serverSocket);
            try {
                threadPool.execute(new ClientHandler(clientSocket,serverIO));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public int getPort() {
        return this.port;
    }

}
