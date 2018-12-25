package ru.basejava.storage;

import java.io.File;

public class SerializationStorageTest extends AbstractFileStorageTest {

    public SerializationStorageTest() {
        super(new SerializationStorage(new File(DIR)));
    }
}
