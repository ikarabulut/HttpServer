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

    private ServerWrapper serverWrapper = mock(ServerWrapper.class);
    private ServerIO serverIO = mock(ServerIO.class);
    @Test
    @DisplayName("When a new Server object is created, the passed port int will provided port number, in this case 5000")
    void createNewServerObject() {
        int port = 5000;
        Server server = new Server(port, serverWrapper, serverIO);

        assertEquals(port, server.getPort());
    }


}
