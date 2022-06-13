package com.ikarabulut.server;

import com.ikarabulut.io.ServerIO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServerTest {

    private ServerFactory serverFactory = mock(ServerFactory.class);
    private ServerIO serverIO = mock(ServerIO.class);
    @Test
    @DisplayName("When a new Server object is created, the passed port int will provided port number, in this case 5000")
    void createNewServerObject() {
        int port = 5000;
        Server server = new Server(port, serverFactory, serverIO);

        assertEquals(port, server.getPort());
    }


}
