package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The Server class starts the server and creates a new thread for each request
 */
class Server {

    private static ServerSocket serverSocket;
    private final JasonDatabase database = new JasonDatabase();
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public void startServer() {
        System.out.println("Server started!");
        createServerSocket();
        try {
            while (!serverSocket.isClosed()) {
                final Socket clientSocket = serverSocket.accept();
                if (clientSocket != null) {
                    executorService.execute(new Session(clientSocket, database));
                }
            }
            executorService.shutdown();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createServerSocket() {
        final String address = "127.0.0.1";
        final int port = 35696;
        try {
            serverSocket = new ServerSocket(port, 50, InetAddress.getByName(address));
        } catch (Exception ignored) {
            System.out.println("[SERVER] Can't create a socket!");
        }

    }

    public static void exit() {
        try {
            serverSocket.close();
        } catch (Exception ignored) {

        }
    }


}
