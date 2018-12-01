package ru.basejava.storage;

import org.junit.Test;
import ru.basejava.exception.StorageException;
import ru.basejava.model.Resume;

import static org.junit.Assert.fail;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(AbstractArrayStorage storage) {
        super(storage);
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
}