package ru.basejava.storage;

import ru.basejava.exception.ExistStorageException;
import ru.basejava.exception.NotExistStorageException;
import ru.basejava.exception.StorageException;
import ru.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage {

    final static int CAPACITY = 10_000;
    Resume[] storage = new Resume[CAPACITY];
    int size;

    abstract void save(int index, Resume resume);

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if(index < 0) {
            if(size < CAPACITY) {
                save(index, resume);
                size++;
            } else {
                throw new StorageException(resume.getUuid());
            }
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if(index > -1) {
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if(index > -1) {
            storage[index] = resume;
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    abstract void delete(int index);

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if(index > -1) {
            size--;
            delete(index);
            storage[size] = null;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    abstract int getIndex(String uuid);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
