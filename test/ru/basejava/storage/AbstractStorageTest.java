package ru.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.basejava.ResumeTestData;
import ru.basejava.exception.ExistStorageException;
import ru.basejava.exception.NotExistStorageException;
import ru.basejava.model.Resume;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {

    final static File DIR = new File(".\\storage");

    AbstractStorage storage;
    int sizeBeforeTest;

    private static final String UUID_1 = "uuid1";
    private Resume resume1 = ResumeTestData.getInstance(UUID_1, "Ivanov Ivan Ivanovich");

    private static final String UUID_2 = "uuid2";
    private Resume resume2 = ResumeTestData.getInstance(UUID_2, "Petrov Petr Sidorovich");

    private static final String UUID_3 = "uuid3";
    private Resume resume3 = ResumeTestData.getInstance(UUID_3, "Subbotin Dmitry Serheevich");

    public AbstractStorageTest(AbstractStorage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        sizeBeforeTest = storage.size();
    }

    @Test
    public void save() {
        storage.save(resume3);
        assertEquals(sizeBeforeTest + 1, storage.size());
        Resume tmp = storage.get(UUID_3);
        assertEquals(resume3, tmp);
    }

    @Test(expected = ExistStorageException.class)
    public void shouldDontSaveResumeIfItAlreadyExist() {
        storage.save(resume1);
    }

    @Test
    public void get() {
        assertEquals(resume1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void shouldDontGetResumeIfItMissing() {
        storage.get(UUID_3);
    }

    @Test
    public void update() {
        Resume resume = storage.get(UUID_1);
        resume.setFullName("newName");
        storage.update(resume);
        assertEquals(resume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void shouldDontUpdateResumeIfItMissing() {
        Resume resume = new Resume(UUID_3, "newName");
        storage.update(resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void shouldDontDeleteResumeIfItMissing() {
        storage.delete(UUID_3);
    }

    @Test
    public void clearStorage() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void getAllResume() {
        List<Resume> actualResumes = Arrays.asList(resume1, resume2);
        assertEquals(actualResumes, storage.getAllSorted());
    }
}
