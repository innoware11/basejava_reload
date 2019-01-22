package ru.basejava.storage;

import ru.basejava.storage.Strategies.XmlStrategyImpl;

public class XmlPathStorageTest extends AbstractStorageTest {

    public XmlPathStorageTest() {
        super(new PathStorage(DIR.getAbsolutePath(), new XmlStrategyImpl()));
    }
}