package ru.basejava.storage;

import ru.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage {

    private final int CAPACITY = 10_000;
    Resume[] storage = new Resume[CAPACITY];
    int size;

    abstract void save(int index, Resume resume);

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if(index == -1) {
            if(size < CAPACITY) {
                save(index, resume);
                size++;
            } else {
                System.out.println("Storage is full");
            }
        } else {
            System.out.println("Resume with uuid = " + resume.getUuid() + " already exists");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if(index > -1) {
            return storage[index];
        }
        System.out.println("Resume with uuid = " + uuid + " doesn't exist");
        return null;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if(index > -1) {
            storage[index] = resume;
        } else {
            System.out.println("Resume with uuid = " + resume.getUuid() + " doesn't exist");
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
            System.out.println("Resume with uuid = " + uuid + " doesn't exist");
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
