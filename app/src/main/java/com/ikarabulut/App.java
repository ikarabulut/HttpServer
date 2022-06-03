package com.ikarabulut;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        int PORT = 5000;
        ClientThreadFactory clientThreadFactory = new ClientThreadFactory();
        ExecutorService threadPool = Executors.newCachedThreadPool(clientThreadFactory);

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            ServerIO io = new ServerIO();
            EchoServer echo = new EchoServer(serverSocket);
            System.out.println("You are running on port: " + PORT);
            while (true) {
                System.out.println("You have " + clientThreadFactory.getNumOfThreads() + " threads open");
                Socket clientSocket = echo.createClientSocket();
                threadPool.submit(new ClientHandler(clientSocket, io));
            }
        } catch (IOException ex) {
            System.err.print(ex.getMessage());
        }
    }

}
