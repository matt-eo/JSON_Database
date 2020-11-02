package server;

import client.Arguments;

import javax.imageio.IIOException;

import java.io.IOException;

import static server.Response.Status.*;

/**
 * The DatabaseManager performs actions on the JSonDatabase based
 * on request type
 */
class DatabaseManager {

    private final JasonDatabase storage;
    private boolean exit;
    private Response response;

    public DatabaseManager(JasonDatabase storage) {
        this.storage = storage;
        this.exit = false;
    }

    public void start(Arguments args) {
        String command = args.type;
        switch (command) {
            case "set":
                setCell(args.key, args.value);
                break;
            case "get":
                getCell(args.key);
                break;
            case "delete":
                deleteCell(args.key);
                break;
            case "exit":
                exit();
                break;
            default:
                response = new Response(ERROR, "Unknown command");
        }
    }

    private void setCell(String key, String value) {

        storage.set(key, value);
        response = new Response(OK);
    }

    private void getCell(String key) {
        String value = storage.get(key);
        if (value != null) {
            response = new Response(OK, value);
        } else {
            response = new Response(ERROR, "No such key");
        }
    }

    private void deleteCell(String key) {
        if (storage.delete(key)) {
            response = new Response(OK);
        } else {
            response = new Response(ERROR, "No such key");
        }
    }

    private void exit() {
        response = new Response(OK);
        setExit();
    }

    private void setExit() {
        exit = true;
    }

    public boolean isExit() {
        return exit;
    }

    public Response getResponse() {
        return this.response;
    }

}
