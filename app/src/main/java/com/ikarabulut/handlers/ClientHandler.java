package com.ikarabulut.handlers;

import com.ikarabulut.io.ClientReader;
import com.ikarabulut.response.Response;
import com.ikarabulut.server.Router;
import com.ikarabulut.io.ClientWriter;
import com.ikarabulut.parser.RequestParser;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.inputStream = clientSocket.getInputStream();
        this.outputStream = clientSocket.getOutputStream();
    }

    public void run() {
        try {
            ClientReader clientReader = new ClientReader(inputStream);
            RequestParser requestParser = new RequestParser(clientReader);
            Map<String, String> initialLine = requestParser.parseInitialLine();

            Router router = new Router(initialLine);
            Response response = router.routeRequest();

            ClientWriter clientWriter = new ClientWriter(outputStream);

            if (response == null) {
                clientWriter.printOutput("server error");
                System.out.println("Response Sent: " + "\r\n" + "server error");
            } else {
                clientWriter.printOutput(response.stringifyResponse());
                System.out.println("Response Sent: " + "\r\n" + response.stringifyResponse());
            }
            closeClientConnection(clientWriter);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void closeClientConnection(ClientWriter writer) throws IOException {
        writer.closeWriter();
        inputStream.close();
        clientSocket.close();
    }

}
