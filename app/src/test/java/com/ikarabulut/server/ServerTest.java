package com.ikarabulut.server;

import com.ikarabulut.io.ServerIO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServerTest {
    private SocketFactory socketFactory = mock(SocketFactory.class);
    private ServerIO serverIO = mock(ServerIO.class);

    @Test
    @DisplayName("When a new Server object is created, the server should be bound to the provided port")
    void createNewServerObject() {
        int port = 5000;
        Server server = new Server(port, socketFactory, serverIO);

        assertEquals(port, server.getPort());
    }

}
