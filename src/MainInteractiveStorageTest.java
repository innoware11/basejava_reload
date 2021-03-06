import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Interactive test for ArrayStorage implementation
 * (just run, no need to understand)
 */
public class MainInteractiveStorageTest {

    private final static ArrayStorage storage = new ArrayStorage();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] commands = {"1. save <uuid>", "2. get <uuid>", "3. delete <uuid>",
            "4. clear", "5. getAll", "6. size", "7. exit"};

        while (true) {
            showMenu(commands);
            String[] params = inputCommand(reader);

            if(params.length < 1 || params.length > 2) {
                System.out.println("Invalid command");
                continue;
            }

            String paramUuid = null;
            if(params.length > 1) {
                paramUuid = params[1];
            }

            switch(params[0]) {
                case "save":
                    saveResume(paramUuid);
                    printAll();
                    break;
                case "get":
                    System.out.println("Resume: " + storage.get(paramUuid));
                    break;
                case "delete":
                    storage.delete(paramUuid);
                    printAll();
                    break;
                case "clear":
                    storage.clear();
                    System.out.println("Storage cleaned");
                    printAll();
                    break;
                case "getAll":
                    printAll();
                    break;
                case "size":
                    System.out.println("Count of resumes = " + storage.size());
                    break;
                case "exit":
                    reader.close();
                    return;
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    private static void showMenu(String[] operations) {
        System.out.println("\nInput the command:");
        for(String operation : operations) {
            System.out.println(operation);
        }
        System.out.print("> ");
    }

    private static String[] inputCommand(BufferedReader reader) throws IOException {
        return reader.readLine().trim().split(" ");
    }

    private static void saveResume(String paramUuid) {
        Resume resume = new Resume();
        resume.uuid = paramUuid;
        storage.save(resume);
    }

    private static void printAll() {
        System.out.println("----------------------------");
        if(storage.size() == 0) {
            System.out.println("The store is empty");
        } else {
            System.out.println("Resume list:");
            for(Resume resume : storage.getAll()) {
                System.out.println(resume);
            }
        }

        System.out.println("----------------------------");
    }
}