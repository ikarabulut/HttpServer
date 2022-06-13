package com.ikarabulut.response.status;

public enum StatusCode {
    OK("200");

    final String code;

    StatusCode(String statusCode) {
        this.code = statusCode;
;    }

    public String getStatusNumber() {
        return code;
    }

}
