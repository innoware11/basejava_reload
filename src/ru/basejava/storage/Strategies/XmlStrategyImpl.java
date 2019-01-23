package ru.basejava.storage.Strategies;

import ru.basejava.model.*;
import ru.basejava.util.XmlBinder;

import java.io.*;

public class XmlStrategyImpl implements StorageStrategy {

    private XmlBinder xmlBinder;

    public XmlStrategyImpl() {
        xmlBinder = new XmlBinder(Resume.class, SimpleTextSection.class, MarkedTextSection.class,
                    OrganizationSection.class, Link.class, Organization.class, Organization.Position.class);
    }

    @Override
    public void writeStream(Resume resume, OutputStream os) throws IOException {
        try(Writer writer = new OutputStreamWriter(os)) {
            xmlBinder.marshal(resume, writer);
        }
    }

    @Override
    public Resume readStream(InputStream is) throws IOException {
        try(Reader reader = new InputStreamReader(is)) {
            return xmlBinder.unmarshal(reader);
        }
    }
}
