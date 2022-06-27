package com.ikarabulut.response;


public interface Response {
    String CRLF = "\r\n";
    String SPACE = " ";
    String EMPTYLINE = "";

    String stringifyResponse();

    boolean hasBody();

}
