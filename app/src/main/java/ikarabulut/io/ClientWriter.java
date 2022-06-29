package ikarabulut.io;

import java.io.*;
import java.net.Socket;

public class ClientWriter {
    OutputStream outputStream;
    PrintWriter socketWriter;

    public ClientWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
        this.socketWriter = new PrintWriter(outputStream);

    }

    public void printOutput(String input) {
        socketWriter.write(input);
        socketWriter.flush();
    }

    public void closeWriter() {
        socketWriter.close();
    }


}
