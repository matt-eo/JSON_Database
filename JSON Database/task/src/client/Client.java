package client;

import com.google.gson.Gson;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client sends request to Server in JSon format
 */
class Client {

    private final int PORT = 35696;
    private final String ADDRESS = "127.0.0.1";
    private String request;
    private Arguments args;
    private Gson gson;

    public void startClient() {
        try (
            Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())
        ) {
            System.out.println("Client started!");

            gson = new Gson();

            args = Main.getArguments();
            if (args.fileName != null) {
                request = getRequest(args.fileName);
            } else {
                request = gson.toJson(args);
            }

            outputStream.writeUTF(request);
            System.out.println("Sent: " + request);

            String received = inputStream.readUTF();
            System.out.println("Received: " + received);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRequest(String fileName) {
        String path = "/home/matteo/IdeaProjects/JSON Database1/JSON Database/task/src/client/data/" + fileName;
        File file = new File(path);
        String request = "";

        try (Scanner scan = new Scanner(file)) {
            request = scan.nextLine();
        } catch (FileNotFoundException f) {
            System.out.println("File not found");
            f.printStackTrace();
        }

        return request;
    }


}


