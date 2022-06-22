package com.ikarabulut.io;

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
        StringBuilder sb = new StringBuilder();
        int c;
        int bytesRead = 0;
        while (bytesRead < availableBytes) {
            c = requestReader.read();
            if (c == -1) break;
            sb.append((char) c);
            bytesRead++;
        }
        System.out.println("Incoming Request: " + "\r\n" + sb);
        return sb.toString();
    }

}
