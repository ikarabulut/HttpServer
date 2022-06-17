package com.ikarabulut.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ClientInputTest {
    private String input = "HEAD /test HTTP/1.1\r\n" +
            "Content-Type" + ":" + " text/plain\r\n" +
            "User-Agent" + ":" + " PostmanRuntime/7.29.0\r\n" +
            "\r\n";

    @Test
    @DisplayName("buildRequestString() should return a string matching the char ar    ray from the BufferedReader")
    void buildRequestString() throws IOException {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ClientInput clientInput = new ClientInput(in);
        String expectedString = "HEAD /test HTTP/1.1\r\nContent-Type: text/plain\r\nUser-Agent: PostmanRuntime/7.29.0\r\n\r\n";

        String returnedString = clientInput.stringifyInput();

        assertEquals(expectedString, returnedString);
    }

}
