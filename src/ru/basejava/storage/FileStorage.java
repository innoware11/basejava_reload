package ru.basejava.storage;

import ru.basejava.exception.StorageException;
import ru.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage {

    private File dir;

    public FileStorage(File dir) {
        if(!dir.exists()) {
            throw new StorageException(dir.getAbsolutePath() + " doesn't exist");
        } else if(!dir.isDirectory()) {
            throw new StorageException(dir.getAbsolutePath() + " is not a folder");
        }
        this.dir = dir;
    }

    @Override
    void doSave(Object uuid, Resume resume) {
        File newFile = new File(dir, resume.getUuid());
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            throw new StorageException("", e);
        }
    }

    @Override
    void clear() {
        File[] files = dir.listFiles();
        for(File file : Objects.requireNonNull(files)) {
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
        List<Resume> resumes = new ArrayList<>();
        for(File file : Objects.requireNonNull(files)) {
            if(file.isFile()) {
                resumes.add(doGet(file.getName()));
            }
        }
        return resumes;
    }

    @Override
    void doUpdate(Object name, Resume resume) {
        doSave(name, resume);
    }

    @Override
    void doDelete(Object name) {
        File[] files = dir.listFiles();
        for(File file : Objects.requireNonNull(files)) {
            if(file.getName().equals(name)) {
                file.delete();
            }
        }
    }

    @Override
    boolean isExist(Object name) {
        return name != null;
    }

    @Override
    String getSearchKey(String uuid) {
        String[] listFiles = dir.list();
        for(String name : Objects.requireNonNull(listFiles)) {
            if(name.equals(uuid)) {
                return uuid;
            }
        }
        return null;
    }

    @Override
    Resume doGet(Object uuid) {
        return new Resume((String)uuid, "");
    }
}
