package ru.basejava.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.Writer;

public class XmlBinder {

    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    public XmlBinder(Class... classes) {
        try {
            JAXBContext context = JAXBContext.newInstance(classes);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            unmarshaller = context.createUnmarshaller();
        } catch(JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    public void marshal(Object instance, Writer write) {
        try {
            marshaller.marshal(instance, write);
        } catch(JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    public <S> S unmarshal(Reader reader) {
        try {
            return (S)unmarshaller.unmarshal(reader);
        } catch(JAXBException e) {
            throw new IllegalStateException(e);
        }
    }
}
