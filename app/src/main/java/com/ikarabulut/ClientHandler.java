package com.ikarabulut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private PrintWriter writer;
    private BufferedReader reader;
    private ServerIO ioStream;
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket, ServerIO io) throws IOException {
        ioStream = io;
        this.clientSocket = clientSocket;
        writer = io.generateClientSocketWriter(clientSocket);
        reader = io.generateClientSocketReader(clientSocket);
    }

    public void run() {
        String inputLine;
        ioStream.printOutput(writer, "You are connected to the Server, any input will be echoed back to you. To exit the connection please enter '.'");
        try {
            while ((inputLine = ioStream.readInput(reader)) != null) {
                if (".".equals(inputLine)) {
                    ioStream.printOutput(writer, "Thank you for using my Echo Server, Disconnecting");
                    closeClientConnection();
                    System.out.println("A client connection has been disconnected");
                    break;
                }
                ioStream.printOutput(writer, inputLine);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void closeClientConnection() throws IOException {
        clientSocket.close();
        writer.close();
        reader.close();
    }

    public BufferedReader getReader() {
        return reader;
    }

    public PrintWriter getWriter() {
        return writer;
    }
}
