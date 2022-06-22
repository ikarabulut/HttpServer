package com.ikarabulut.io;

import java.io.*;
import java.net.Socket;

public class ClientWriter {
    OutputStream outputStream;
    PrintWriter socketWriter;

    public ClientWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
        boolean useAutoFlush = true;
        this.socketWriter = new PrintWriter(outputStream, useAutoFlush);

    }

    public void printOutput(String input) {
        socketWriter.print(input);
    }

    public void closeWriter() {
        socketWriter.close();
    }


}
