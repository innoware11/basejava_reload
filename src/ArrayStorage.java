public class ArrayStorage {

    Resume[] storage = new Resume[10_000];

    void save(Resume resume) {
    }

    Resume get(String uuid) {
        return null;
    }

    void delete(String uuid) {
    }

    void clear() {
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return new Resume[0];
    }

    int size() {
        return 0;
    }
}
