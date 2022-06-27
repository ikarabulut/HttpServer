package com.ikarabulut.server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServerTest {
    int PORT = 5000;
    private SocketFactory socketFactory = mock(SocketFactory.class);

    @Test
    @DisplayName("When a new Server object is created, this.port should be equal to the port argument")
    void createNewServerObject() {
        Server server = new Server(PORT, socketFactory);

        assertEquals(PORT, server.getPort());
    }

    @Test
    @DisplayName("When bind() is called, then the SocketFactory will have createServerSocket called")
    void bind_ServerSocketIsGenerated() throws IOException {
        ServerSocket serverSocket = mock(ServerSocket.class);
        when(socketFactory.createServerSocket(PORT)).thenReturn(serverSocket);
        Server server = new Server(PORT, socketFactory);
        Server spyServer = Mockito.spy(server);

        spyServer.bind();

        verify(socketFactory).createServerSocket(PORT);
    }

    @Test
    @DisplayName("When bind() is called, then the server should be able accept a connection, creating a Socket if available")
    void bind_ClientSocketIsAccepted() throws IOException {
        ServerSocket serverSocket = mock(ServerSocket.class);
        Socket clientSocket = mock(Socket.class);
        when(socketFactory.createServerSocket(PORT)).thenReturn(serverSocket);
        when(socketFactory.createClientSocket(serverSocket)).thenReturn(clientSocket);
        Server server = new Server(PORT, socketFactory);
        Server spyServer = Mockito.spy(server);

        spyServer.bind();

        verify(socketFactory.createClientSocket(serverSocket));
    }

}
