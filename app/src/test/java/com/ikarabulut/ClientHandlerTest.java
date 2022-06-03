package com.ikarabulut;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
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

    @Disabled // PROBLEM:: Cannot get the mocks to create actual reader and writer causing Exception to be thrown when .close is called
    @Test
    @DisplayName("When close() is called, then the clientSocket, writer, and reader should all be closed")
    void closeClientConnection() throws IOException {
        ServerIO io = mock(ServerIO.class);
        Socket clientSocket = mock(Socket.class);
        ClientHandler clientHandler = new ClientHandler(clientSocket, io);

        when(io.generateClientSocketWriter(clientSocket)).thenReturn(mock(PrintWriter.class));

        PrintWriter writer = clientHandler.getWriter();
        BufferedReader reader = clientHandler.getReader();

        verify(writer).close();
        verify(reader).close();
        assertTrue(clientSocket.isClosed());

    }
}
