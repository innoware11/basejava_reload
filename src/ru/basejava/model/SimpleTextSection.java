package ru.basejava.model;

import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

public class SimpleTextSection extends AbstractSection {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "content", type = String.class)
    private String simpleText;

    public SimpleTextSection() {
    }

    public SimpleTextSection(String simpleText) {
        Objects.requireNonNull(simpleText, "simpleText can't be null");
        this.simpleText = simpleText;
    }

    public String getSimpleText() {
        return simpleText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleTextSection that = (SimpleTextSection) o;
        return Objects.equals(simpleText, that.simpleText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(simpleText);
    }

    @Override
    public String toString() {
        return simpleText;
    }
}
