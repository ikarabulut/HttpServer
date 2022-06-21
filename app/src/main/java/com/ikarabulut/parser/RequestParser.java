package com.ikarabulut.parser;

import com.ikarabulut.io.ClientReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {
    private ClientReader clientReader;
    private String requestString;

    public RequestParser(ClientReader clientReader) {
        this.clientReader = clientReader;
        try {
            requestString = clientReader.stringifyInput();
        } catch (IOException ex) {
            System.err.print("Unable to parse InputStream into String");
            requestString = "unable to parse";
        }
    }

    public Map<String, String> parseInitialLine() {
        Map<String, String> initialLineMap = new HashMap<>();
        String[] splitInitialLine = requestString.split("\r?\n");
        String[] initialLineArray = splitInitialLine[0].split(" ", 3);

        initialLineMap.put("httpMethod", initialLineArray[0]);
        initialLineMap.put("httpPath", initialLineArray[1]);
        initialLineMap.put("httpVersion", initialLineArray[2]);

        return initialLineMap;
    }

    public Map<String, String> parseHeaders() {
        Map<String, String> headers = new HashMap<>();
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
    }

}
