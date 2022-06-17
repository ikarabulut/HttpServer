package com.ikarabulut.handlers;

import com.ikarabulut.server.http.Router;
import com.ikarabulut.io.ServerIO;
import com.ikarabulut.parser.RequestParser;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ClientHandler implements Runnable {
    private ServerIO serverIO;
    private PrintWriter writer;
    private BufferedReader reader;
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket, ServerIO serverIO) throws IOException {
        this.clientSocket = clientSocket;
        this.serverIO = serverIO;
        writer = serverIO.generateClientSocketWriter(clientSocket);
        reader = serverIO.generateClientSocketReader(clientSocket);
    }

    public void run() {
        BufferedReader request = reader;
        RequestParser requestParser = new RequestParser(request);
        Map<String, String> initialLine = requestParser.parseInitialLine();
        Map<String, String> headers = requestParser.parseHeaders();

        Router router = new Router(initialLine);
        String response = router.routeRequest();

        System.out.println("Response sent:" + response);
        serverIO.printOutput(writer, response);
    }

    public void closeClientConnection() throws IOException {
        clientSocket.close();
        writer.close();
        reader.close();
    }

}
