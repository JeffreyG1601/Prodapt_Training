import java.util.List;

public class DisplayRecord {
    public static void display(List<Employee> employees, String designation) {
        System.out.println("\n--- Employee Records (" + designation + ") ---");

        employees.stream()
                 .filter(emp -> emp.getDesignation().equalsIgnoreCase(designation))
                 .forEach(System.out::println);

        System.out.println("============================");
    }
}
