package com.ikarabulut.response;

import java.util.Map;

public class GetResponseBuilder extends GetResponse {

    public GetResponseBuilder(String version, StatusCode statusCode, String statusName, Map<String, String> headers) {
        super(version, statusCode, statusName, headers);
    }

    public GetResponseBuilder body(String body) {
        this.body = body;
        return this;
    }

    public GetResponse build() {
        return new GetResponse(this);
    }

}
