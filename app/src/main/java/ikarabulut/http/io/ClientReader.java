package ikarabulut.http.io;

import java.io.*;

public class ClientReader {
    Reader requestReader;
    int availableBytes;

    public ClientReader(InputStream requestInputStream) throws IOException {
        requestReader = new InputStreamReader(requestInputStream);
        availableBytes = requestInputStream.available();
    }

    public String stringifyInput() throws IOException {
        requestReader = new BufferedReader(requestReader, availableBytes);
        StringBuilder stringBuilder = new StringBuilder();
        int character;
        int bytesRead = 0;
        while (bytesRead < availableBytes) {
            character = requestReader.read();
            if (character == -1) break;
            stringBuilder.append((char) character);
            bytesRead++;
        }
        System.out.println("Incoming Request: " + "\r\n" + stringBuilder);
        return stringBuilder.toString();
    }

}
