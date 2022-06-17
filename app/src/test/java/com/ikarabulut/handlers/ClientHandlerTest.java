package com.ikarabulut.handlers;

import com.ikarabulut.handlers.ClientHandler;
import com.ikarabulut.io.ServerIO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

import static org.mockito.Mockito.*;

class ClientHandlerTest {
    @Test
    @DisplayName("A ClientHandler should generate a writer based on the clientSocket that was passed through the constructor")
    void ClientHandlerConstructor_GeneratesWriter() throws IOException {
        ServerIO io = mock(ServerIO.class);
        Socket clientSocket = mock(Socket.class);

        ClientHandler clientHandler = new ClientHandler(clientSocket, io);

        verify(io).generateClientSocketWriter(clientSocket);
    }

    @Test
    @DisplayName("A ClientHandler should generate a reader based on the clientSocket that was passed through the constructor")
    void ClientHandlerConstructor_GeneratesReader() throws IOException {
        ServerIO io = mock(ServerIO.class);
        Socket clientSocket = mock(Socket.class);

        ClientHandler clientHandler = new ClientHandler(clientSocket, io);

        verify(io).generateClientSocketReader(clientSocket);
    }

    @Test
    @DisplayName("When closeClientConnection() is called, then the clientSocket should have close() invoked on it")
    void closeClientConnection() throws IOException {
        ServerIO io = mock(ServerIO.class);
        Socket clientSocket = mock(Socket.class);

        ClientHandler clientHandler = new ClientHandler(clientSocket, io);

        clientHandler.closeClientConnection();

        verify(clientSocket).close();
    }

}
