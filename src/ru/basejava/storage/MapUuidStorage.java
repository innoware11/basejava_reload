package ru.basejava.storage;

import ru.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public void save(Object uuid, Resume resume) {
        storage.put((String)uuid, resume);
    }

    @Override
    public Resume get(Object uuid) {
        return storage.get(uuid.toString());
    }

    @Override
    public void update(Object uuid, Resume resume) {
        storage.replace((String)uuid, resume);
    }

    @Override
    public void delete(Object uuid) {
        storage.remove(uuid.toString());
    }

    @Override
    public boolean isExist(Object uuid) {
        return storage.containsKey(uuid.toString());
    }

    @Override
    public String getSearchKey(String uuid) {
        return uuid;
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
