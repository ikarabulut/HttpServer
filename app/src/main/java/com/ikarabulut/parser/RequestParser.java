package com.ikarabulut.parser;

import com.ikarabulut.io.ClientReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {
    private ClientReader clientReader;
    private String rawRequest;

    public RequestParser(ClientReader clientReader) {
        this.clientReader = clientReader;
        try {
            rawRequest = clientReader.stringifyInput();
        } catch (IOException ex) {
            System.err.print("Unable to parse InputStream into String");
            rawRequest = "unable to parse";
        }
    }

    public Map<String, String> parseInitialLine() {
        String[] lines = rawRequest.split("\r?\n");
        String firstLine = lines[0];
        String[] firstLineParts = firstLine.split(" ", 3);
        Map<String, String> initialLine = new HashMap<>();

        initialLine.put("httpMethod", firstLineParts[0]);
        initialLine.put("httpPath", firstLineParts[1]);
        initialLine.put("httpVersion", firstLineParts[2]);

        return initialLine;
    }

    public Map<String, String> parseHeaders() {
        String[] splitRequest = rawRequest.split("\r?\n");
        Map<String, String> headers = new HashMap<>();

        for (int i = 1; i < splitRequest.length; i++) {
            String headerLine = splitRequest[i];
            if (headerLine.equals("")) {
                break;
            } else {
                String[] line = splitRequest[i].split(": ", 2);
                String headerName = line[0];
                String headerValue = line[1];
                headers.put(headerName, headerValue);
            }
        }
        return headers;
    }

}
