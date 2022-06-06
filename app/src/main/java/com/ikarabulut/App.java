package com.ikarabulut;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.SECONDS;

public class App {
    public static void main(String[] args) {
        int PORT = 5000;
        LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 3, 60, SECONDS, linkedBlockingQueue);

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            ServerIO io = new ServerIO();
            EchoServer echo = new EchoServer(serverSocket);
            System.out.println("You are running on port: " + PORT);
            while (true) {
                Socket clientSocket = echo.createClientSocket();
                threadPool.execute(new ClientHandler(clientSocket, io));
                if (threadPool.getActiveCount() == threadPool.getCorePoolSize()) {
                    System.out.println("You have reached the max core pool size");
                    System.out.println("You have " + linkedBlockingQueue.size() + " connections in the queue");
                } else {
                    System.out.println("You have " + threadPool.getActiveCount() + " active threads");
                }
            }
        } catch (IOException ex) {
            System.err.print(ex.getMessage());
        }
    }

}
