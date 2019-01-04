package ru.basejava.storage.Strategies;

import ru.basejava.exception.StorageException;
import ru.basejava.model.Resume;

import java.io.*;

public class SerializationStrategyImpl implements StorageStrategy {

    @Override
    public void writeStream(Resume resume, OutputStream os) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(resume);
        }
    }

    @Override
    public Resume readStream(InputStream is) throws IOException {
        try(ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume)ois.readObject();
        } catch(ClassNotFoundException e) {
            throw new StorageException("Error read resume", e);
        }
    }
}