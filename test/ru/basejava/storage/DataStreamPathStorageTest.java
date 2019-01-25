package ru.basejava.storage;

import ru.basejava.storage.Strategies.DataSerializationStrategyImpl;

public class DataStreamPathStorageTest extends AbstractStorageTest {

    public DataStreamPathStorageTest() {
        super(new PathStorage(DIR.getAbsolutePath(), new DataSerializationStrategyImpl()));
    }
}