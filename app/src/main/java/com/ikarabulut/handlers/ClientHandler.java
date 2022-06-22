package com.ikarabulut.handlers;

import com.ikarabulut.io.ClientReader;
import com.ikarabulut.response.Response;
import com.ikarabulut.server.Router;
import com.ikarabulut.io.ServerIO;
import com.ikarabulut.parser.RequestParser;

import java.io.*;
import java.net.Socket;
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
        try {
            ClientReader clientReader = new ClientReader(clientSocket.getInputStream());
            RequestParser requestParser = new RequestParser(clientReader);
            Map<String, String> initialLine = requestParser.parseInitialLine();

            Router router = new Router(initialLine);
            Response response = router.routeRequest();
            System.out.println(response.stringifyResponse());

            serverIO.printOutput(writer, response.stringifyResponse());
            closeClientConnection();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void closeClientConnection() throws IOException {
        clientSocket.close();
    }

}
