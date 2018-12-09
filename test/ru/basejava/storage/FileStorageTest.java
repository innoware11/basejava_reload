package ru.basejava.storage;

import java.io.File;

public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new FileStorage(new File("D:\\Java\\git\\basejava_reload\\storage")));
    }
}
