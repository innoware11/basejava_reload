package ru.basejava.model;

import java.util.List;
import java.util.Objects;

public class MarkedTextSection extends AbstractSection {

    private static final long serialVersionUID = 1L;

    private List<String> list;

    public MarkedTextSection() {
    }

    public MarkedTextSection(List<String> list) {
        Objects.requireNonNull(list, "list can't be null");
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        MarkedTextSection that = (MarkedTextSection) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
