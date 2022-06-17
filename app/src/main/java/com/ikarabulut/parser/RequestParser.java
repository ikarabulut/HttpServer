package com.ikarabulut.parser;

import com.ikarabulut.io.ClientReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {
    private ClientReader clientReader;
    private BufferedReader request;
    private HashMap<String, String> initialLine;
    private HashMap<String, String> headers;

    public RequestParser(BufferedReader request) {
        this.request = request;
    }

    public RequestParser(ClientReader clientReader) {
        this.clientReader = clientReader;
    }

    public Map<String, String> parseInitialLine() {
        Map<String, String> initialLineMap = new HashMap<>();
        try {
            String requestString = clientReader.stringifyInput();
            String[] splitInitialLine = requestString.split("\r?\n|\r");
            String[] initialLineArray = splitInitialLine[0].split(" ", 3);

            initialLineMap.put("httpMethod", initialLineArray[0]);
            initialLineMap.put("httpPath", initialLineArray[1]);
            initialLineMap.put("httpVersion", initialLineArray[2]);

            return initialLineMap;
        } catch (IOException ex) {
            System.err.print("Unable to parse reader into string");
        }
        initialLineMap.put("error", "unable to parse");
        return initialLineMap;
    }

    public HashMap<String, String> parseHeaders() throws IOException {
        String throwAwayFirstLine = request.readLine();
        String headerString;
        headers = new HashMap<>();
        while (true) {
            headerString = request.readLine();
            if (headerString.equals("")) {
                break;
            }
            String[] splitHeader = headerString.split(": ", 2);
            String headerKey = splitHeader[0];
            String headerValue = splitHeader[1];
            headers.put(headerKey, headerValue);
        }
        return headers;
    }

    public HashMap<String, String> getInitialLine() {
        return initialLine;
    }

    public HashMap<String, String> getHeaders() { return headers; }
}
