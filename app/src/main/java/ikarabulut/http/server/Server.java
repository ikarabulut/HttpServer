package ikarabulut.http.server;

import ikarabulut.http.client.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server {
    private int port;
    private SocketFactory socketFactory;
    private int THREAD_COUNT = 5;

    public Server(int port, SocketFactory socketFactory) {
        this.port = port;
        this.socketFactory = socketFactory;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = socketFactory.createServerSocket(port);

        ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);

        listenForConnections(serverSocket, pool);
    }

    public int getPort() {
        return this.port;
    }

    private void listenForConnections(ServerSocket serverSocket, ExecutorService pool) {
        while (serverSocket.isBound()) {
            try {
                accept(serverSocket, pool);
            } catch (IOException ex) {
                ex.printStackTrace();
                System.err.println("Unable to bind Client Socket");
            }
        }
    }

    private void accept(ServerSocket serverSocket, ExecutorService pool) throws IOException {
        Socket clientSocket = socketFactory.createClientSocket(serverSocket);
        pool.execute(new ClientHandler(clientSocket));
    }

}
