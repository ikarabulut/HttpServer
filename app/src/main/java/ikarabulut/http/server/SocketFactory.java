package ikarabulut.http.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketFactory {

    public ServerSocket createServerSocket(int port) throws IOException {
        return new ServerSocket(port);
    }

    public Socket createClientSocket(ServerSocket serverSocket) throws IOException {
        return serverSocket.accept();
    }

}
