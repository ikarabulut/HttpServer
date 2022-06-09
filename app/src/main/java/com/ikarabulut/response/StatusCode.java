package com.ikarabulut.response;

public enum StatusCode {
    OK("200");

    final String statusCode;

    StatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
