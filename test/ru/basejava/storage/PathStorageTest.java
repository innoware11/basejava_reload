package ru.basejava.storage;

import ru.basejava.storage.Strategies.SerializationStrategyImpl;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage(DIR.getAbsolutePath(), new SerializationStrategyImpl()));
    }
}