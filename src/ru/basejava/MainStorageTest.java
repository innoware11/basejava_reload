package ru.basejava;

import ru.basejava.model.Resume;
import ru.basejava.storage.SortedArrayStorage;

public class MainStorageTest {

    private static final SortedArrayStorage storage = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume resume1 = new Resume("uuid1");
        Resume resume2 = new Resume("uuid2");
        Resume resume3 = new Resume("uuid3");

        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
        printAll();

        System.out.println("Get resume1: " + storage.get(resume1.getUuid()));
        System.out.println("Size: " + storage.size());

        storage.delete(resume1.getUuid());
        storage.update(resume2);
        printAll();

        storage.delete(resume1.getUuid());
        storage.clear();
        printAll();

        System.out.println("Size: " + storage.size());
    }

    private static void printAll() {
        System.out.println("\nGet All:");
        for (Resume resume : storage.getAll()) {
            System.out.println(resume);
        }
    }
}
