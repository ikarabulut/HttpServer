package com.ikarabulut.handlers;

import com.ikarabulut.io.ClientWriter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.net.Socket;

import static org.mockito.Mockito.*;

class ClientHandlerTest {
    @Test
    @DisplayName("When a client handler is created, it should create an input stream and output stream based on the client socket")
    void createClientSocket_CheckInputStream() throws IOException {
        Socket clientSocket = mock(Socket.class);
        ClientHandler clientHandler = new ClientHandler(clientSocket);

        verify(clientSocket).getOutputStream();
        verify(clientSocket).getInputStream();
    }

    @Test
    @DisplayName("When closeClientConnection is called, then the ClientWriters close method should be called")
    void closeClientConnection_CheckClientWriterClose() throws IOException {
        Socket clientSocket = mock(Socket.class);
        InputStream inputStream = mock(InputStream.class);
        when(clientSocket.getInputStream()).thenReturn(inputStream);
        ClientWriter writer = mock(ClientWriter.class);
        ClientWriter writerSpy = Mockito.spy(writer);
        ClientHandler clientHandler = new ClientHandler(clientSocket);

        clientHandler.closeClientConnection(writerSpy);

        verify(writerSpy).closeWriter();
    }

    @Test
    @DisplayName("When closeClientConnection is called, then the Socket close method should be called")
    void closeClientConnection_CheckSocketCloses() throws IOException {
        Socket clientSocket = mock(Socket.class);
        InputStream inputStream = mock(InputStream.class);
        when(clientSocket.getInputStream()).thenReturn(inputStream);
        ClientWriter writer = mock(ClientWriter.class);
        ClientHandler clientHandler = new ClientHandler(clientSocket);

        clientHandler.closeClientConnection(writer);

        verify(clientSocket).close();
    }

    @Test
    @DisplayName("When closeClientConnection is called, then the InputStreams close method should be called")
    void closeClientConnection_CheckReaderCloses() throws IOException {
        Socket clientSocket = mock(Socket.class);
        InputStream inputStream = mock(InputStream.class);
        when(clientSocket.getInputStream()).thenReturn(inputStream);
        ClientWriter writer = mock(ClientWriter.class);
        ClientHandler clientHandler = new ClientHandler(clientSocket);

        clientHandler.closeClientConnection(writer);

        verify(inputStream).close();
    }

    @Test
    @DisplayName("When closeClientConnection is called, then the ClientWriter closeWriter method should be called")
    void closeClientConnection_CheckClientWriterCloses() throws IOException {
        Socket clientSocket = mock(Socket.class);
        InputStream inputStream = mock(InputStream.class);
        when(clientSocket.getInputStream()).thenReturn(inputStream);
        ClientWriter writer = mock(ClientWriter.class);
        ClientWriter writerSpy = Mockito.spy(writer);
        ClientHandler clientHandler = new ClientHandler(clientSocket);

        clientHandler.closeClientConnection(writerSpy);

        verify(writerSpy).closeWriter();
    }


}
