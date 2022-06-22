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

}
