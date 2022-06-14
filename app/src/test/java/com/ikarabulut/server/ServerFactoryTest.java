package com.ikarabulut.server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServerFactoryTest {
    private ServerFactory serverFactory = new ServerFactory();

    @Test
    @DisplayName("A new ServerSocket should be created with the passed port number")
    void createServerSocket() throws IOException {
        int port = 5000;
        ServerSocket serverSocket = serverFactory.createServerSocket(port);

        int createdServerSocketPort = serverSocket.getLocalPort();
        assertEquals(port, createdServerSocketPort);
    }

    @Test
    @DisplayName("A connected Socket should be generated based on the passed serverSocket accepting connections")
    void createClientSocket() throws IOException {
        ServerSocket serverSocket = mock(ServerSocket.class);
        Socket clientSocket = mock(Socket.class);

        when(serverSocket.accept()).thenReturn(clientSocket);
        Socket generatedClientSocket = serverFactory.createClientSocket(serverSocket);

        verify(serverSocket).accept();
        assertNotNull(generatedClientSocket);

    }

}
