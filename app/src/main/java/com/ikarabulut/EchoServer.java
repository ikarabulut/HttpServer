package com.ikarabulut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private Socket clientSocket;
    private ServerSocket serverSocket;
    private PrintWriter echoWriter;
    private BufferedReader echoReader;

    public EchoServer(ServerSocket connectedServerSocket) {
        serverSocket = connectedServerSocket;
    }

    public void createServerEndPoint(ServerIO io) throws IOException {
        clientSocket = createClientSocket(serverSocket);
        echoWriter = io.generateClientSocketWriter(clientSocket);
        echoReader = io.generateClientSocketReader(clientSocket);
    }

    public void beginEcho() throws IOException {
        String inputLine;
        while ((inputLine = echoReader.readLine()) != null) {
            if (".".equals(inputLine)) {
                System.out.println("Thank you for trying my Echo Server");
                break;
            }
            echoWriter.println(inputLine);
        }
    }

    public Socket createClientSocket(ServerSocket serverSocket) {
        try {
            return serverSocket.accept();
        } catch (IOException ex) {
            System.err.print(ex.getMessage());
        }
        return null;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

}
