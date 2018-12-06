package ru.basejava.model;

public enum ContactTypes {

    PHONE_NUMBER("Тел.: +7(921) 855-0482"),
    SKYPE("skype: grigory.kislin"),
    EMAIL("e-mail: gkislin@yandex.ru"),
    LINKEDIN("linkedin: https://www.linkedin.com/in/gkislin/"),
    GITHUB("github: https://github.com/gkislin"),
    STACKOVERFLOW("stackoverflow: https://stackoverflow.com/users/548473/gkislin"),
    HOMEPAGE("homepage: http://gkislin.ru/");

    private String contact;

    ContactTypes(String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }
}