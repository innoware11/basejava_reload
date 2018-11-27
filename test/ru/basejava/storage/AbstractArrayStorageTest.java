package ru.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.basejava.exception.ExistStorageException;
import ru.basejava.exception.NotExistStorageException;
import ru.basejava.exception.StorageException;
import ru.basejava.model.Resume;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {

    private AbstractArrayStorage storage;
    private int sizeBeforeTest;
    private static final String UUID_1 = "uuid1";
    private Resume resume1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private Resume resume2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private Resume resume3 = new Resume(UUID_3);

    public AbstractArrayStorageTest(AbstractArrayStorage storage) {
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
        assertEquals(resume3, storage.get(UUID_3));
    }

    @Test(expected = ExistStorageException.class)
    public void shouldDontSaveResumeIfItAlreadyExist() {
        storage.save(resume1);
    }

    @Test(expected = StorageException.class)
    public void shouldHappenOverflow() {
        try {
            for (int i = sizeBeforeTest; i < AbstractArrayStorage.CAPACITY; i++) {
                storage.save(new Resume("uuid_" + i));
            }
        } catch(Exception e) {
            fail("Error saving");
        }
        storage.save(new Resume("OVERFLOW"));
    }

    @Test
    public void getResume() {
        assertEquals(resume1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void shouldDontGetResumeIfItMissing() {
        storage.get(UUID_3);
    }

    @Test
    public void updateResume() {
        storage.update(resume1);
        assertEquals(resume1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void shouldDontUpdateResumeIfItMissing() {
        storage.update(resume3);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertEquals(resume1, storage.get(UUID_1));
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
        Resume[] actualResumes = {resume1, resume2};
        assertArrayEquals(storage.getAll(), actualResumes);
    }
}