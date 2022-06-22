package com.ikarabulut.response;

import java.util.Date;
import java.util.Map;

public class HeadResponse implements Response {
    protected final String version;
    protected final StatusCode statusCode;
    protected final String statusNumber;
    protected final Map<String, String> headers;

    public HeadResponse(String version, StatusCode statusCode, String statusNumber, Map<String, String> headers) {
        this.version = version;
        this.statusCode = statusCode;
        this.statusNumber = statusNumber;
        this.headers = headers;
    }

    @Override
    public String stringifyResponse() {
        String initialLine = version + SPACE + statusNumber + SPACE + statusCode + CRLF;
        String headers = stringifyHeaders();

        return initialLine + headers;
    }

    @Override
    public boolean hasBody() {
        return false;
    }

    private String stringifyHeaders() {
        StringBuilder headersAsAString = new StringBuilder();
        for (Map.Entry<String, String> set : headers.entrySet()) {
            headersAsAString.append(set.getKey()).append(": ").append(set.getValue()).append(CRLF);
        }
        headersAsAString.append(CRLF).append(CRLF);
        return headersAsAString.toString();
    }

}
