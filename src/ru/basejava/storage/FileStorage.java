package ru.basejava.storage;

import ru.basejava.exception.StorageException;
import ru.basejava.model.Resume;
import ru.basejava.storage.Strategies.StorageStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {

    private File dir;
    private StorageStrategy strategy;

    public FileStorage(File dir, StorageStrategy strategy) {
        if(!dir.isDirectory()) {
            throw new StorageException(dir.getAbsolutePath() + " is not a folder");
        } else if(!dir.canRead() || !dir.canWrite()) {
            throw new StorageException(dir.getAbsolutePath() + " can't be read or write");
        }
        this.dir = dir;
        this.strategy = strategy;
    }

    @Override
    void doSave(File file, Resume resume) {
        try {
            file.createNewFile();
            strategy.writeStream(resume, new BufferedOutputStream(new FileOutputStream((file))));
        } catch (IOException e) {
            throw new StorageException("resume can't be save", e);
        }
    }

    @Override
    Resume doGet(File file) {
        try {
            return strategy.readStream(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    void doUpdate(File file, Resume resume) {
        doSave(file, resume);
    }

    @Override
    void doDelete(File file) {
        File[] files = dir.listFiles();
        Objects.requireNonNull(files);
        for(File currentFile : files) {
            if(currentFile.getName().equals(currentFile.getName())) {
                currentFile.delete();
            }
        }
    }

    @Override
    void clear() {
        File[] files = dir.listFiles();
        Objects.requireNonNull(files);
        for(File file : files) {
            file.delete();
        }
    }

    @Override
    int size() {
        return Objects.requireNonNull(dir.list()).length;
    }

    @Override
    List<Resume> getAll() {
        File[] files = dir.listFiles();
        Objects.requireNonNull(files);
        List<Resume> resumes = new ArrayList<>();
        for(File file : files) {
            if(file.isFile()) {
                resumes.add(doGet(file));
            }
        }
        return resumes;
    }

    @Override
    boolean isExist(File file) {
        return file.exists();
    }

    @Override
    File getSearchKey(String uuid) {
        return new File(dir, uuid);
    }
}