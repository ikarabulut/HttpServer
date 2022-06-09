package com.ikarabulut.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class HeadResponseHandlerTest {
    @Test
    @DisplayName("With a valid path, stringifyHeadResponse should return a string with a status line and headers")
    void stringifyHeadResponse() {
        boolean isValidPath = true;
        String httpVersion = "HTTP/1.1";
        Date date = new Date();

        HeadResponseHandler headResponseHandler = new HeadResponseHandler(isValidPath, httpVersion);

        String expectedResponse = "HTTP/1.1 200 OK\r\n" +
                                    "Date: " + date.toString() + "\r\n";
        String receivedResponse = headResponseHandler.stringifyHeadResponse();

        assertEquals(expectedResponse, receivedResponse);
    }

}
