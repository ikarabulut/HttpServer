package com.ikarabulut;

import java.io.BufferedReader;
import java.io.IOException;
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
        String inputLine;
        try {
            inputLine = serverIO.readInput(reader);
            serverIO.printOutput(writer, inputLine);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void closeClientConnection() throws IOException {
        clientSocket.close();
        writer.close();
        reader.close();
    }

}
