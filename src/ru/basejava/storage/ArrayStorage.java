package ru.basejava.storage;

import ru.basejava.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    void save(int index, Resume resume) {
        storage[size] = resume;
    }

    @Override
    void delete(int index) {
        storage[index] = storage[size];
    }

    @Override
    int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if(storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }


}
