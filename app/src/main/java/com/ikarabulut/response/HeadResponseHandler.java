package com.ikarabulut.response;

import java.util.Date;

public class HeadResponseHandler {
    private String CRLF = "\r\n";
    private String httpVersion;
    private boolean isValidPath;
    private String initialResponseLine;
    private String responseHeaderLines;


    public HeadResponseHandler(boolean isValidPath, String httpVersion) {
        this.httpVersion = httpVersion;
        this.isValidPath = isValidPath;

        generateInitialResponseLine();
        generateResponseHeaderLines();
    }

    public String stringifyHeadResponse() {
        return initialResponseLine + responseHeaderLines;
    }

    private void generateInitialResponseLine() {
        String statusCode = StatusCode.OK.getStatusCode();
        String result = String.valueOf(StatusCode.OK);

        initialResponseLine = httpVersion + " " + statusCode + " " + result + CRLF;
    }

    private void generateResponseHeaderLines() {
        Date date = new Date();
        String dateHeader = "Date: " + date.toString();

        responseHeaderLines = dateHeader + CRLF;
    }
}
