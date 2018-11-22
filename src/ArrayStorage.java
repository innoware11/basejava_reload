import java.util.Arrays;

public class ArrayStorage {

    private final int CAPACITY = 10_000;
    private Resume[] storage = new Resume[CAPACITY];
    private int size;

    public void save(Resume resume) {
        if(getIndex(resume.getUuid()) == -1) {
            if(size < CAPACITY) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("Storage is full");
            }
        } else {
            System.out.println("Resume with uuid = " + resume.getUuid() + " already exists");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if(index > -1) {
            return storage[index];
        }
        System.out.println("Resume with uuid = " + uuid + " doesn't exist");
        return null;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if(index > -1) {
            storage[index] = resume;
        } else {
            System.out.println("Resume with uuid = " + resume.getUuid() + " doesn't exist");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if(index > -1) {
            size--;
            storage[index] = storage[size];
            storage[size] = null;
        } else {
            System.out.println("Resume with uuid = " + uuid + " doesn't exist");
        }
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if(storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
