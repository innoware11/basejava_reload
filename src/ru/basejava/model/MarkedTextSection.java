package ru.basejava.model;

import java.util.List;
import java.util.Objects;

public class MarkedTextSection extends AbstractSection {

    private List<String> list;

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
}
