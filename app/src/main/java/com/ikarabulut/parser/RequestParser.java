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
        //TODO: add this.requestString so the reader is only read once and then saved as an object
    }

    public Map<String, String> parseInitialLine() {
        Map<String, String> initialLineMap = new HashMap<>();
        try {
            String requestString = clientReader.stringifyInput();
            String[] splitInitialLine = requestString.split("\r?\n");
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

    public Map<String, String> parseHeaders() {
        Map<String, String> headers = new HashMap<>();
        try {
            String requestString = clientReader.stringifyInput();
            String[] splitRequestString = requestString.split("\r?\n");

            for (int i = 1; i < splitRequestString.length; i++) {
                if (splitRequestString[i].equals("")) {
                    break;
                } else {
                    String[] line = splitRequestString[i].split(": ", 2);
                    headers.put(line[0], line[1]);
                }
            }
            return headers;
        } catch (IOException ex) {
            System.err.print("Unable to parse reader into string");
        }
        headers.put("error", "unable to parse");
        return headers;
    }

    public HashMap<String, String> getInitialLine() {
        return initialLine;
    }

    public HashMap<String, String> getHeaders() { return headers; }
}
