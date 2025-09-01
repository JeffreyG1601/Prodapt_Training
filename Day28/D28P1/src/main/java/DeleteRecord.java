import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class DeleteRecord {
    public static void delete(List<Employee> employees, String designation, Scanner sc) {
        System.out.println("Enter ID to be deleted: ");
        int id = sc.nextInt();

        System.out.print("Confirm Choice (Y/N): ");
        String ch = sc.next();

        boolean confirmed = Stream.of("y", "yes")
                                  .anyMatch(opt -> opt.equalsIgnoreCase(ch));

        if (confirmed) {
            boolean removed = employees.removeIf(emp -> emp.getId() == id && emp.getDesignation().equalsIgnoreCase(designation));
            if (removed) {
                System.out.println("Data Deleted");
            } else {
                System.out.println("No record found for that ID and designation");
            }
        } else {
            System.out.println("Data Not Deleted");
        }
        System.out.println("============================");
    }
}
