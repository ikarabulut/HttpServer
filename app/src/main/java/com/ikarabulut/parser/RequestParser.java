package com.ikarabulut.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class RequestParser {
    private BufferedReader request;
    private HashMap<String, String> initialLine;
    private HashMap<String, String> headers;

    public RequestParser(BufferedReader request) {
        this.request = request;
    }

    public void parseInitialLine() throws IOException {
        String initialLineString = request.readLine();
        initialLine = new HashMap<>();
        String[] splitRequestLine = initialLineString.split(" ", 3);
        initialLine.put("httpMethod", splitRequestLine[0]);
        initialLine.put("httpPath", splitRequestLine[1]);
        initialLine.put("httpVersion", splitRequestLine[2]);
    }

    public void parseHeaders() throws IOException {
        String throwAwayFirstLine = request.readLine();
        String headerString;
        headers = new HashMap<>();
        while (true) {
            headerString = request.readLine();
            if (headerString.equals("")) {
                break;
            }
            String[] splitHeader = headerString.split(" ", 2);
            String headerKey = splitHeader[0];
            String headerValue = splitHeader[1];
            headers.put(headerKey, headerValue);
        }
    }

    public HashMap<String, String> getInitialLine() {
        return initialLine;
    }

    public HashMap<String, String> getHeaders() { return headers; }
}
