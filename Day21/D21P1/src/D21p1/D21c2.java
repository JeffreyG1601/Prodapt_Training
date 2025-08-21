package D21p1;

import java.util.*;

class Employee {
    int eid;
    String ename;
    double salary;
    String designation;

    Employee(int eid, String ename, double salary, String designation) {
        this.eid = eid;
        this.ename = ename;
        this.salary = salary;
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "ID: " + eid + ", Name: " + ename + ", Salary: " + salary + ", Designation: " + designation;
    }
}

public class D21c2 {
    private static List<Employee> employees = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Employee Payroll System ---");
            System.out.println("1. Insert Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Find Highest Salary");
            System.out.println("4. Find Lowest Salary");
            System.out.println("5. Delete Employee");
            System.out.println("6. Display All Employees");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: insertEmployee(); break;
                case 2: updateEmployee(); break;
                case 3: findHighestSalary(); break;
                case 4: findLowestSalary(); break;
                case 5: deleteEmployee(); break;
                case 6: displayAll(); break;
                case 7: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // Insert new employee
    private static void insertEmployee() {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        for (Employee e : employees) {
            if (e.eid == id) {
                System.out.println("Employee with this ID already exists!");
                return;
            }
        }
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Salary: ");
        double sal = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Designation: ");
        String desig = sc.nextLine();

        employees.add(new Employee(id, name, sal, desig));
        System.out.println("Employee added successfully!");
    }

    // Update employee
    private static void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        for (Employee e : employees) {
            if (e.eid == id) {
                System.out.print("Enter new name (leave blank to keep same): ");
                String name = sc.nextLine();
                if (!name.trim().isEmpty()) e.ename = name;

                System.out.print("Enter new salary (-1 to keep same): ");
                double sal = sc.nextDouble();
                sc.nextLine();
                if (sal != -1) e.salary = sal;

                System.out.print("Enter new designation (leave blank to keep same): ");
                String desig = sc.nextLine();
                if (!desig.trim().isEmpty()) e.designation = desig;

                System.out.println("Employee updated successfully!");
                return;
            }
        }
        System.out.println("Employee not found!");
    }

    // Find highest salary
    private static void findHighestSalary() {
        if (employees.isEmpty()) {
            System.out.println("No employees found!");
            return;
        }
        Employee highest = Collections.max(employees, Comparator.comparingDouble(e -> e.salary));
        System.out.println("Highest Salary Employee: " + highest);
    }

    // Find lowest salary
    private static void findLowestSalary() {
        if (employees.isEmpty()) {
            System.out.println("No employees found!");
            return;
        }
        Employee lowest = Collections.min(employees, Comparator.comparingDouble(e -> e.salary));
        System.out.println("Lowest Salary Employee: " + lowest);
    }

    // Delete employee
    private static void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();
        Iterator<Employee> it = employees.iterator();
        while (it.hasNext()) {
            Employee e = it.next();
            if (e.eid == id) {
                it.remove();
                System.out.println("Employee deleted successfully!");
                return;
            }
        }
        System.out.println("Employee not found!");
    }

    // Display all employees
    private static void displayAll() {
        if (employees.isEmpty()) {
            System.out.println("No records found!");
            return;
        }
        System.out.println("All Employees:");
        for (Employee e : employees) {
            System.out.println(e);
        }
    }
}
