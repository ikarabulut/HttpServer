package com.ikarabulut;

import java.io.*;
import java.net.Socket;

public class ServerIO {
    public String readInput(BufferedReader input) throws IOException {
        return input.readLine();
    }

    public void printOutput(PrintWriter output, String input) {
        output.println(input);
    }

    public PrintWriter generateClientSocketWriter(Socket clientSocket) throws IOException {
        OutputStream clientOutputStream = clientSocket.getOutputStream();
        boolean useAutoFlush = true;
        return new PrintWriter(clientOutputStream, useAutoFlush);
    }

    public BufferedReader generateClientSocketReader(Socket clientSocket) throws IOException {
        InputStream clientInputStream = clientSocket.getInputStream();
        InputStreamReader clientInputStreamReader = new InputStreamReader(clientInputStream);
        return new BufferedReader(clientInputStreamReader);
    }

}
