package ru.basejava.storage;

import ru.basejava.exception.StorageException;
import ru.basejava.model.Resume;

import java.io.*;

public class SerializationStorage extends AbstractFileStorage {

    public SerializationStorage(File dir) {
        super(dir);
    }

    @Override
    void writeStream(Resume resume, OutputStream os) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(resume);
        }
    }

    @Override
    Resume readStream(InputStream is) throws IOException {
        try(ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume)ois.readObject();
        } catch(ClassNotFoundException e) {
            throw new StorageException("Error read resume", e);
        }
    }
}
