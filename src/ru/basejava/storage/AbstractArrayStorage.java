package ru.basejava.storage;

import ru.basejava.exception.StorageException;
import ru.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {

    final static int CAPACITY = 10_000;
    Resume[] storage = new Resume[CAPACITY];
    int size;

    abstract void insert(int index, Resume resume);

    @Override
    public void save(Object index, Resume resume) {
        if(size < CAPACITY) {
            insert((int)index, resume);
            size++;
        } else {
            throw new StorageException(resume.getUuid());
        }
    }

    @Override
    public Resume get(Object index) {
        return storage[(int)index];
    }

    @Override
    public void update(Object index, Resume resume) {
        storage[(int)index] = resume;
    }

    abstract void remove(int index);

    @Override
    public void delete(Object index) {
        size--;
        remove((int)index);
        storage[size] = null;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public List<Resume> getAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    public int size() {
        return size;
    }

    @Override
    boolean isExist(Object index) {
        return (int)index > -1;
    }
}
