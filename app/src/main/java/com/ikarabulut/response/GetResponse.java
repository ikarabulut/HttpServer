package com.ikarabulut.response;

import java.util.Map;

public class GetResponse implements Response {
    protected final String version;
    protected final StatusCode statusCode;
    protected final String statusNumber;
    protected final Map<String, String> headers;
    protected String body;

    public GetResponse(String version, StatusCode statusCode, String statusNumber, Map<String, String> headers) {
        this.version = version;
        this.statusCode = statusCode;
        this.statusNumber = statusNumber;
        this.headers = headers;
    }

    public GetResponse(GetResponseBuilder getResponseBuilder) {
        this(getResponseBuilder.version, getResponseBuilder.statusCode, getResponseBuilder.statusNumber, getResponseBuilder.headers);
        this.body = getResponseBuilder.body;
    }

    @Override
    public String stringifyResponse() {
        String initialLine = version + SPACE + statusNumber + SPACE + statusCode + CRLF;
        String headers = stringifyHeaders();

        return hasBody() ? initialLine + headers + body + CRLF : initialLine + headers;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public StatusCode getStatusCode() {
        return this.statusCode;
    }

    @Override
    public String getStatusNumber() {
        return this.statusNumber;
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public String getBody() {
        return this.body;
    }

    private String stringifyHeaders() {
        StringBuilder headersAsAString = new StringBuilder();
        for (Map.Entry<String, String> set : headers.entrySet()) {
            headersAsAString.append(set.getKey()).append(": ").append(set.getValue()).append(CRLF);
        }
        headersAsAString.append(CRLF).append(CRLF);
        return headersAsAString.toString();
    }

    private boolean hasBody() {
        return body != null;
    }

}
