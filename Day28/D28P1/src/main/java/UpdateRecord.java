import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class UpdateRecord {
    public static void update(List<Employee> employees, String designation, Scanner sc) {
        System.out.println("Enter ID to be updated: ");
        int id = sc.nextInt();

        System.out.print("Confirm Choice (Y/N): ");
        String ch = sc.next();

        boolean confirmed = Stream.of("y", "yes")
                                  .anyMatch(opt -> opt.equalsIgnoreCase(ch));

        if (confirmed) {
            System.out.print("Enter new salary: ");
            int newSalary = sc.nextInt();

            Optional<Employee> empOpt = employees.stream()
                    .filter(emp -> emp.getId() == id && emp.getDesignation().equalsIgnoreCase(designation))
                    .findFirst();

            if (empOpt.isPresent()) {
                empOpt.get().setSalary(newSalary);
                System.out.println("Salary updated");
            } else {
                System.out.println("No record found for that ID and designation");
            }
        } else {
            System.out.println("Data Not Updated");
        }
        System.out.println("============================");
    }
}
