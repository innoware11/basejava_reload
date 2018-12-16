package ru.basejava.storage;

import ru.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public void doSave(Resume searchKey, Resume resume) {
        storage.put((resume).getUuid(), resume);
    }

    @Override
    public Resume doGet(Resume searchKey) {
        return (Resume)searchKey;
    }

    @Override
    public void doUpdate(Resume searchKey, Resume resume) {
        storage.replace(((Resume)searchKey).getUuid(), resume);
    }

    @Override
    public void doDelete(Resume searchKey) {
        storage.remove(((Resume)searchKey).getUuid());
    }

    @Override
    public boolean isExist(Resume searchKey) {
        return searchKey != null;
    }

    @Override
    public Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    public void clear() {
        storage.clear();
    }

    public List<Resume> getAll() {
        return new ArrayList<>(storage.values());
    }

    public int size() {
        return storage.size();
    }
}
