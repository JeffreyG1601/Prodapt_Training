import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class MainMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();

        // Menu options mapped to actions
        Map<Integer, BiConsumer<String, Scanner>> operations = new HashMap<>();
        operations.put(1, (designation, scanner) -> CreateRecord.create(employees, designation, scanner));
        operations.put(2, (designation, scanner) -> UpdateRecord.update(employees, designation, scanner));
        operations.put(3, (designation, scanner) -> DeleteRecord.delete(employees, designation, scanner));
        operations.put(4, (designation, scanner) -> DisplayRecord.display(employees, designation));

        while (true) {
            System.out.println("\n===== Employee Menu =====");
            System.out.println("1) Create");
            System.out.println("2) Raise Salary");
            System.out.println("3) Delete");
            System.out.println("4) Display");
            System.out.println("5) Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            if (choice == 5) {
                System.out.println("Exiting program...");
                break;
            }

            // designation
            System.out.println("\nSelect Designation:");
            System.out.println("1) Clerk");
            System.out.println("2) Manager");
            System.out.println("3) Developer");
            System.out.println("4) Tester");
            System.out.print("Enter designation choice: ");
            int desigChoice = sc.nextInt();

            String designation = Stream.of("Clerk", "Manager", "Developer", "Tester")
                                       .skip(desigChoice - 1)
                                       .findFirst()
                                       .orElse("Invalid");

            if (designation.equals("Invalid")) {
                System.out.println("Invalid designation choice.");
                continue;
            }

            // execute mapped operation
            operations.getOrDefault(choice, (d, s) -> System.out.println("Invalid choice"))
                      .accept(designation, sc);
        }
    }
}
