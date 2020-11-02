package server;

import com.google.gson.Gson;

import java.io.*;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * This class is responsible for creating the database file as well as
 * writing, deleting and retrieving data
 */
class JasonDatabase {

    private final String path = "data/db.json";
    private File file = new File(path);
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public JasonDatabase() {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("{}");
            writer.close();

        } catch (IOException ignored) {

        }
    }


    public void set(String key, String value) {
        writeLock.lock();

        try {

            FileReader reader = new FileReader(file);
            Map<String, String> storage = new Gson().fromJson(reader, Map.class);
            reader.close();

            storage.put(key, value);
            FileWriter writer = new FileWriter(file);
            writer.write(new Gson().toJson(storage));
            writer.close();

        } catch (FileNotFoundException i) {
            System.out.println("File not found");
            i.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public String get(String key) {
        readLock.lock();
        String value = null;
        try {
            FileReader reader = new FileReader(file);
            Map<String, String> storage = new Gson().fromJson(reader, Map.class);
            reader.close();

            value = storage.get(key);

        } catch (FileNotFoundException i) {
            System.out.println("File not found");
            i.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
        return value;
    }

    public boolean delete(String key) {
        writeLock.lock();
        boolean isDeleted = false;

        try {
            FileReader reader = new FileReader(file);
            Map<String, String> storage = new Gson().fromJson(reader, Map.class);
            reader.close();

            if (storage.containsKey(key)) {
                storage.remove(key);
                isDeleted = true;


                FileWriter writer = new FileWriter(file);
                writer.write(new Gson().toJson(storage));
                writer.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }

        return isDeleted;
    }


}
