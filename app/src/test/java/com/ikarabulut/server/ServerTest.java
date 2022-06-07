package com.ikarabulut.server;

import com.ikarabulut.ServerIO;
import com.ikarabulut.server.Server;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServerTest {
    @Test
    @DisplayName("When a new EchoServer object is created, a connection will be made to the provided port number, in this case 5000")
    void ObjectCreationOpensNewConnection() throws IOException {
        int port = 5000;
        ServerIO mockIo = mock(ServerIO.class);
        ServerSocket serverSocket = new ServerSocket(port);
        Server server = new Server(serverSocket);
        ServerSocket connectedServerSocket = server.getServerSocket();

        assertEquals(port, connectedServerSocket.getLocalPort());
    }

    @Test
    @DisplayName("When a server socket accepts requests, a client socket will be returned")
    void createClientSocket() throws IOException {
        ServerIO mockIo = mock(ServerIO.class);
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        Server server = new Server(mockServerSocket);
        Socket newClientSocket = new Socket();

        when(mockServerSocket.accept()).thenReturn(newClientSocket);

        assertNotNull(server.createClientSocket());
        assertEquals(newClientSocket, server.createClientSocket());


    }


}
