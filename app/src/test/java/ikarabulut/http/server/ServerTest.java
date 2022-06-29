package ikarabulut.http.server;

import ikarabulut.server.Server;
import ikarabulut.server.SocketFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.ServerSocket;

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

        spyServer.start();

        verify(socketFactory).createServerSocket(PORT);
    }

}
