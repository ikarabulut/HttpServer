package com.ikarabulut.response;


public interface Response {
    String CRLF = "\r\n";
    String SPACE = " ";
    String stringifyResponse();

    boolean hasBody();

}
