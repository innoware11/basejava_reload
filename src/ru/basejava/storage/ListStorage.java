package ru.basejava.storage;

import ru.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    @Override
    public void save(Object index, Resume resume) {
        storage.add(resume);
    }

    @Override
    public Resume get(Object index) {
        return storage.get((int)index);
    }

    @Override
    public void update(Object index, Resume resume) {
        storage.set((int)index, resume);
    }

    @Override
    public void delete(Object index) {
        storage.remove((int)index);
    }

    @Override
    public boolean isExist(Object index) {
        return index != null;
    }

    @Override
    public Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if(storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    public void clear() {
        storage.clear();
    }

    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    public int size() {
        return storage.size();
    }
}
