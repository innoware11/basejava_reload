package ru.basejava.storage.Strategies;

import ru.basejava.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StorageStrategy {

    void writeStream(Resume resume, OutputStream os) throws IOException;

    Resume readStream(InputStream is) throws IOException;
}
