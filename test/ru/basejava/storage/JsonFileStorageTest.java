package ru.basejava.storage;

import ru.basejava.storage.Strategies.JsonStrategyImpl;

import java.io.File;

public class JsonFileStorageTest extends AbstractStorageTest {

    public JsonFileStorageTest() {
        super(new FileStorage(new File(DIR.getAbsolutePath()), new JsonStrategyImpl()));
    }
}