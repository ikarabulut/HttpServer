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
    @DisplayName("When close() is called, then the clientSocket, writer, and reader should all have .close() called on them")
    void closeClientConnection() throws IOException {
        ServerIO io = mock(ServerIO.class);
        Socket clientSocket = mock(Socket.class);
        PrintWriter writer = mock(PrintWriter.class);
        BufferedReader reader = mock(BufferedReader.class);

        when(io.generateClientSocketWriter(clientSocket)).thenReturn(writer);
        when(io.generateClientSocketReader(clientSocket)).thenReturn(reader);
        ClientHandler clientHandler = new ClientHandler(clientSocket, io);

        clientHandler.closeClientConnection();

        verify(writer).close();
        verify(reader).close();
        verify(clientSocket).close();
    }

}
