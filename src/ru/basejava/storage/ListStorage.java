package ru.basejava.storage;

import ru.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private List<Resume> storage = new ArrayList<>();

    @Override
    public void doSave(Integer index, Resume resume) {
        storage.add(resume);
    }

    @Override
    public Resume doGet(Integer index) {
        return storage.get((int)index);
    }

    @Override
    public void doUpdate(Integer index, Resume resume) {
        storage.set((int)index, resume);
    }

    @Override
    public void doDelete(Integer index) {
        storage.remove((int)index);
    }

    @Override
    public boolean isExist(Integer index) {
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

    public List<Resume> getAll() {
        return new ArrayList<>(storage);
    }

    public int size() {
        return storage.size();
    }
}
