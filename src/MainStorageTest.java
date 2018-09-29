public class MainStorageTest {

    static final ArrayStorage storage = new ArrayStorage();

    public static void main(String[] args) {
        Resume resume1 = new Resume();
        resume1.uuid = "uuid1";

        Resume resume2 = new Resume();
        resume2.uuid = "uuid2";

        Resume resume3 = new Resume();
        resume3.uuid = "uuid3";

        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
        printAll();

        System.out.println("Get resume1: " + storage.get(resume1.uuid));
        System.out.println("Size: " + storage.size());

        storage.delete(resume1.uuid);
        printAll();

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
