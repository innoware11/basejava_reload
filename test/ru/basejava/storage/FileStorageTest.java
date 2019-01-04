package ru.basejava.storage;

import ru.basejava.storage.Strategies.SerializationStrategyImpl;

public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new FileStorage(DIR, new SerializationStrategyImpl()));
    }
}
