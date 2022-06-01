package com.ikarabulut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private ServerIO io;
    private Socket clientSocket;
    private ServerSocket serverSocket;
    private PrintWriter echoWriter;
    private BufferedReader echoReader;

    public EchoServer(ServerSocket connectedServerSocket, ServerIO io) {
        serverSocket = connectedServerSocket;
        this.io = io;
    }

    public void createServerEndPoint() {
        try {
            clientSocket = createClientSocket(serverSocket);
            echoWriter = io.generateClientSocketWriter(clientSocket);
            echoReader = io.generateClientSocketReader(clientSocket);
        } catch (IOException ex) {
            System.err.print("Unable to create server endpoint");
        }
    }

    public void beginEcho() throws IOException {
        String inputLine;
        while ((inputLine = io.readInput(echoReader)) != null) {
            if (".".equals(inputLine)) {
                System.out.println("Thank you for trying my Echo Server");
                break;
            }
            io.printOutput(echoWriter, inputLine);
        }
    }

    public Socket createClientSocket(ServerSocket serverSocket) throws IOException {
        return serverSocket.accept();
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

}
