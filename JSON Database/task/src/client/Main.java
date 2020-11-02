package client;

import com.beust.jcommander.JCommander;

public class Main {

    final static Client client = new Client();
    final static Arguments arguments = new Arguments();

    public static void main(String[] args) {
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);

        client.startClient();
    }

    public static Arguments getArguments() {
        return arguments;
    }
}
