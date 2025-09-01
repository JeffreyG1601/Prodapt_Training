import java.util.*;
import java.util.stream.IntStream;

public class CreateRecord {
    public static void create(List<Employee> employees, String designation, Scanner sc) {
        System.out.print("Enter number of records to be entered: ");
        int count = sc.nextInt();

        IntStream.range(0, count).forEach(i -> {
            System.out.println("============================");
            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();

            System.out.print("Enter Name: ");
            String name = sc.next();

            System.out.print("Enter Age: ");
            int age = sc.nextInt();

            System.out.print("Enter Salary: ");
            int salary = sc.nextInt();

            employees.add(new Employee(id, name, age, salary, designation));
        });

        System.out.println("============================");
        System.out.println("Data Inserted Successfully!");
        System.out.println("============================");
    }
}
