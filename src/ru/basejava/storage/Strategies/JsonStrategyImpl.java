package ru.basejava.storage.Strategies;

import ru.basejava.model.Resume;
import ru.basejava.util.JsonBinder;

import java.io.*;

public class JsonStrategyImpl implements StorageStrategy {

    @Override
    public void writeStream(Resume resume, OutputStream os) throws IOException {
        try(Writer writer = new OutputStreamWriter(os)) {
            JsonBinder.write(writer, resume);
        }
    }

    @Override
    public Resume readStream(InputStream is) throws IOException {
        try(Reader reader = new InputStreamReader(is)) {
            return JsonBinder.read(reader, Resume.class);
        }
    }
}
