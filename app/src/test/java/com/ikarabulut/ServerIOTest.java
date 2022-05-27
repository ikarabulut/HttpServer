package com.ikarabulut;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ServerIOTest {

    @Test
    @DisplayName("When a new Input stream is created, and 'hello' is passed. the system.in should be 'hello'")
    void readInputStream_WithHello() {
        ServerIO io = new ServerIO();
        String inputStream = "Hello";
        BufferedReader input = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(inputStream.getBytes())));

        assertEquals("Hello", io.readInput(input));
    }

    @Test
    @DisplayName("When an input of 1 is passed, then readInputStream should return 1")
    void readInputStream_With1() {
        ServerIO io = new ServerIO();
        String inputStream = "1";
        BufferedReader input = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(inputStream.getBytes())));

        assertEquals("1", io.readInput(input));
    }

    @Test
    @DisplayName("When an input of 'Hello' is received, then 'Hello' should be printed")
    void writeOutput_WithHello() {
        ServerIO io = new ServerIO();
        String inputStream = "Hello";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(out, true);

        io.printOutput(printWriter, inputStream);

        assertEquals("Hello\n", out.toString());
    }

    @Test
    @DisplayName("When an input of '1' is received, then '1' should be printed")
    void writeOutput_With1() {
        ServerIO io = new ServerIO();
        String inputStream = "1";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(out, true);

        io.printOutput(printWriter, inputStream);

        assertEquals("1\n", out.toString());
    }

    @Test
    @DisplayName("When a socket writer gets created, then null should not be returned")
    void createSocketWriter() throws IOException {
        ServerIO io = new ServerIO();
        Socket mockSocket = mock(Socket.class);
        OutputStream mockOutputStream = mock(OutputStream.class);
        when(mockSocket.getOutputStream()).thenReturn(mockOutputStream);

        assertNotNull(io.generateClientSocketWriter(mockSocket));
    }

    @Test
    @DisplayName("When a socket reader gets created, then null should not be returned")
    void createSocketReader() throws IOException {
        ServerIO io = new ServerIO();
        Socket mockSocket = mock(Socket.class);
        InputStream mockInputStream = mock(InputStream.class);

        when(mockSocket.getInputStream()).thenReturn(mockInputStream);

        assertNotNull(io.generateClientSocketReader(mockSocket));
    }


}
