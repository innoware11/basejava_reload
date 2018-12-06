package ru.basejava.model;

public enum SectionTypes {

    OBJECTIVE("Позиция"),
    PERSONAL("Личные качества"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATION("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private String headline;

    SectionTypes(String headline) {
        this.headline = headline;
    }

    public String getHeadline() {
        return headline;
    }
}