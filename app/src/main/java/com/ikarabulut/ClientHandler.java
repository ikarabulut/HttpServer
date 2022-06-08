package com.ikarabulut;

import com.ikarabulut.parser.RequestParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private PrintWriter writer;
    private BufferedReader reader;
    private ServerIO serverIO;
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket, ServerIO serverIO) throws IOException {
        this.serverIO = serverIO;
        this.clientSocket = clientSocket;
        writer = serverIO.generateClientSocketWriter(clientSocket);
        reader = serverIO.generateClientSocketReader(clientSocket);
    }

    public void run() {
        BufferedReader request = reader;
        try {
            System.out.println(request.readLine());
            System.out.println(request.readLine());
            System.out.println(request.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        RequestParser requestParser = new RequestParser(request);
    }

    public void closeClientConnection() throws IOException {
        clientSocket.close();
        writer.close();
        reader.close();
    }

}
