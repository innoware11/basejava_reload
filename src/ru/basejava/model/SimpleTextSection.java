package ru.basejava.model;

import java.util.Objects;

public class SimpleTextSection extends AbstractSection {

    private static final long serialVersionUID = 1L;

    private String simpleText;

    public SimpleTextSection(String simpleText) {
        Objects.requireNonNull(simpleText, "simpleText can't be null");
        this.simpleText = simpleText;
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
