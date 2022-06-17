package com.ikarabulut.io;

import java.io.*;

//TODO:: Rename to ClientReader
public class ClientInput {
    Reader requestReader;

    public ClientInput(InputStream requestInputStream) throws IOException {
        requestReader = new InputStreamReader(requestInputStream);
    }

    public String stringifyInput() throws IOException {
        requestReader = new BufferedReader(requestReader);
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = requestReader.read()) != -1) sb.append((char) c);
        return sb.toString();
    }
}
