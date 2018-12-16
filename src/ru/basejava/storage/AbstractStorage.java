package ru.basejava.storage;

import ru.basejava.exception.ExistStorageException;
import ru.basejava.exception.NotExistStorageException;
import ru.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> {

    abstract void clear();

    abstract int size();

    abstract List<Resume> getAll();

    abstract void doSave(SK searchKey, Resume resume);

    private static final Comparator<Resume> COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    private static final Logger log = Logger.getLogger(AbstractStorage.class.getName());
    
    public void save(Resume resume) {
        log.info("save " + resume);
        SK searchKey = receiveNotExistSearchKey(resume.getUuid());
        doSave(searchKey, resume);
    }

    abstract Resume doGet(SK searchKey);

    public Resume get(String uuid) {
        log.info("get " + uuid);
        SK searchKey = receiveExistSearchKey(uuid);
        return doGet(searchKey);
    }

    abstract void doUpdate(SK searchKey, Resume resume);

    public void update(Resume resume) {
        log.info("update " + resume);
        SK searchKey = receiveExistSearchKey(resume.getUuid());
        doUpdate(searchKey, resume);
    }

    abstract void doDelete(SK searchKey);

    public void delete(String uuid) {
        log.info("delete " + uuid);
        SK searchKey = receiveExistSearchKey(uuid);
        doDelete(searchKey);
    }

    abstract boolean isExist(SK searchKey);

    abstract SK getSearchKey(String uuid);
    
    private SK receiveExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if(!isExist(searchKey)) {
            log.warning("Resume with uuid = " + uuid + " doesn't exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK receiveNotExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if(isExist(searchKey)) {
            log.warning("Resume with uuid = " + uuid + " already exists");
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