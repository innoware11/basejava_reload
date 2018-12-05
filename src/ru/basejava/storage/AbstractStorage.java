package ru.basejava.storage;

import ru.basejava.exception.ExistStorageException;
import ru.basejava.exception.NotExistStorageException;
import ru.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage {

    abstract void clear();

    abstract int size();

    abstract List<Resume> getAll();

    abstract void save(Object searchKey, Resume resume);

    private static final Comparator<Resume> COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
    
    public void save(Resume resume) {
        Object searchKey = receiveNotExistSearchKey(resume.getUuid());
        save(searchKey, resume);
    }

    abstract Resume get(Object searchKey);

    public Resume get(String uuid) {
        Object searchKey = receiveExistSearchKey(uuid);
        return get(searchKey);
    }

    abstract void update(Object searchKey, Resume resume);

    public void update(Resume resume) {
        Object searchKey = receiveExistSearchKey(resume.getUuid());
        update(searchKey, resume);
    }

    abstract void delete(Object searchKey);

    public void delete(String uuid) {
        Object searchKey = receiveExistSearchKey(uuid);
        delete(searchKey);
    }

    abstract boolean isExist(Object searchKey);

    abstract Object getSearchKey(String uuid);
    
    private Object receiveExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if(!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object receiveNotExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if(isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    public List<Resume> getAllSorted() {
        List<Resume> resumes = getAll();
        resumes.sort(COMPARATOR);
        return resumes;
    }
}