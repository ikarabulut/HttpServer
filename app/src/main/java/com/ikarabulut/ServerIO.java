package com.ikarabulut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerIO {
    public String readInput(BufferedReader input) {
        try {
            return input.readLine();
        } catch (IOException ex) {
            System.err.print(ex.getMessage());
        }
        return null;
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
