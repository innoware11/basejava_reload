package ru.basejava.storage;

import ru.basejava.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void insert(int index, Resume resume) {
        storage[size] = resume;
    }

    @Override
    public void remove(int index) {
        storage[index] = storage[size];
    }

    @Override
    public Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if(storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
