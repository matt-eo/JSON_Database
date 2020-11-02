package server;

import client.Arguments;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *  A new thread
 */
class Session implements Runnable {

    private final Socket socket;
    private String received;
    private Arguments args;
    private Gson gson;
    private DatabaseManager databaseManager;

    public Session(Socket socketForClient, JasonDatabase database) {
        this.socket = socketForClient;
        this.gson = new Gson();
        this.databaseManager = new DatabaseManager(database);
    }

    public void run() {
        try (
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())
        ) {
            received = inputStream.readUTF();
            args = gson.fromJson(received, Arguments.class);
            databaseManager.start(args);
            Response response = databaseManager.getResponse();

            if (databaseManager.isExit()) {
                outputStream.writeUTF(gson.toJson(response));
                socket.close();
                Server.exit();
            } else {
                outputStream.writeUTF(gson.toJson(response));
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
