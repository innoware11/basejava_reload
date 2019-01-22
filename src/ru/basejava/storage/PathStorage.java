package ru.basejava.storage;

import ru.basejava.exception.StorageException;
import ru.basejava.model.Resume;
import ru.basejava.storage.Strategies.StorageStrategy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {

    private Path dir;

    private StorageStrategy strategy;

    public PathStorage(String dir, StorageStrategy strategy) {
        this.dir = Paths.get(dir);
        Objects.requireNonNull(dir, this.dir.getFileName() + " can't be null");
        if(!Files.isDirectory(this.dir)) {
            throw new IllegalArgumentException(dir + " is not a folder");
        }
        this.strategy = strategy;
    }

    @Override
    void doSave(Path path, Resume resume) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("resume with uuid = " + resume.getUuid() + " can't be save", e);
        }
        doUpdate(path, resume);
    }

    @Override
    Resume doGet(Path path) {
        try {
            return strategy.readStream(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("resume with uuid = " + path.getFileName() + " can't be get", e);
        }
    }

    @Override
    void doUpdate(Path path, Resume resume) {
        try {
            strategy.writeStream(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("resume with uuid = " + resume.getUuid() + " can't be update", e);
        }
    }

    @Override
    void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("resume with uuid = " + path.getFileName() + " can't be delete", e);
        }
    }

    @Override
    void clear() {
        getFilesList().forEach(this::doDelete);
    }

    @Override
    int size() {
        return (int) getFilesList().count();
    }

    @Override
    List<Resume> getAll() {
        return getFilesList().map(this::doGet).collect(Collectors.toList());
    }

    @Override
    boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    Path getSearchKey(String uuid) {
        return dir.resolve(uuid);
    }

    private Stream<Path> getFilesList() {
        try {
            return Files.list(dir);
        } catch (IOException e) {
            throw new StorageException("Files can't be read", e);
        }
    }
}