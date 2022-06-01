package com.ikarabulut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerIO {
    public String readInput(BufferedReader input) throws IOException {
        return input.readLine();
    }

    public void printOutput(PrintWriter output, String input) {
        output.println(input);
    }

    public PrintWriter generateClientSocketWriter(Socket clientSocket) throws IOException {
        return new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public BufferedReader generateClientSocketReader(Socket clientSocket) throws IOException {
        return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

}
