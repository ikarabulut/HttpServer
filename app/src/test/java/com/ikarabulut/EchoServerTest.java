package com.ikarabulut;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EchoServerTest {
    @Test
    @DisplayName("When a new EchoServer object is created, a connection will be made to the provided port number, in this case 5000")
    void ObjectCreationOpensNewConnection() throws IOException {
        int port = 5000;
        ServerIO mockIo = mock(ServerIO.class);
        ServerSocket serverSocket = new ServerSocket(port);
        EchoServer echoServer = new EchoServer(serverSocket);
        ServerSocket connectedServerSocket = echoServer.getServerSocket();

        assertEquals(port, connectedServerSocket.getLocalPort());
    }

    @Test
    @DisplayName("When a server socket accepts requests, a client socket will be returned")
    void createClientSocket() throws IOException {
        ServerIO mockIo = mock(ServerIO.class);
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        EchoServer echoServer = new EchoServer(mockServerSocket);
        Socket newClientSocket = new Socket();

        when(mockServerSocket.accept()).thenReturn(newClientSocket);

        assertNotNull(echoServer.createClientSocket());
        assertEquals(newClientSocket, echoServer.createClientSocket());


    }


}
