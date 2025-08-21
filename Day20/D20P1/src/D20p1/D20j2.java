package D20p1;

import java.util.*;

abstract class Emp {
    Scanner sc = new Scanner(System.in);
    int uid, age;
    String name;
    double salary;
    String desig;

    Emp() {
        System.out.print("Enter ID: "); uid = sc.nextInt();
        System.out.print("Enter Name: "); name = sc.next();
        System.out.print("Enter Age: "); age = sc.nextInt();
    }

    public void display() {
        System.out.println("ID : " + uid);
        System.out.println("NAME : " + name);
        System.out.println("AGE : " + age);
        System.out.println("SALARY : " + salary);
        System.out.println("DESIGNATION : " + desig);
    }

    abstract void raisesalary();
    abstract void leavesleft();
}

class Clerk extends Emp {
    Clerk() {
        System.out.print("Enter Salary: "); salary = sc.nextDouble();
        desig = "Clerk";
    }
    void raisesalary() {
        System.out.print("Increase Salary by: ");
        double extra = sc.nextDouble();
        double oldSalary = salary;
        salary += extra;
        System.out.println("Updated Salary: " + salary);
        System.out.println("Increment %: " + (extra / oldSalary * 100) + "%");
    }
    void leavesleft() { System.out.println("Leaves Left (Clerk): 4"); }
}

class Tester extends Emp {
    Tester() {
        System.out.print("Enter Salary: "); salary = sc.nextDouble();
        desig = "Tester";
    }
    void raisesalary() {
        System.out.print("Increase Salary by: ");
        double extra = sc.nextDouble();
        double oldSalary = salary;
        salary += extra;
        System.out.println("Updated Salary: " + salary);
        System.out.println("Increment %: " + (extra / oldSalary * 100) + "%");
    }
    void leavesleft() { System.out.println("Leaves Left (Tester): 5"); }
}

class Developer extends Emp {
    Developer() {
        System.out.print("Enter Salary: "); salary = sc.nextDouble();
        desig = "Developer";
    }
    void raisesalary() {
        System.out.print("Increase Salary by: ");
        double extra = sc.nextDouble();
        double oldSalary = salary;
        salary += extra;
        System.out.println("Updated Salary: " + salary);
        System.out.println("Increment %: " + (extra / oldSalary * 100) + "%");
    }
    void leavesleft() { System.out.println("Leaves Left (Developer): 6"); }
}

class Manager extends Emp {
    Manager() {
        System.out.print("Enter Salary: "); salary = sc.nextDouble();
        desig = "Manager";
    }
    void raisesalary() {
        System.out.print("Increase Salary by: ");
        double extra = sc.nextDouble();
        double oldSalary = salary;
        salary += extra;
        System.out.println("Updated Salary: " + salary);
        System.out.println("Increment %: " + (extra / oldSalary * 100) + "%");
    }
    void leavesleft() { System.out.println("Leaves Left (Manager): 8"); }
}

public class D20j2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Emp> employees = new ArrayList<>();

        int ch1;
        do {
            System.out.println("\n=== EMPLOYEE MENU ===");
            System.out.println("1) Create Employee");
            System.out.println("2) Display Employees");
            System.out.println("3) Raise Salary");
            System.out.println("4) Leaves Left");
            System.out.println("5) Exit");
            System.out.println("6) Delete Employee");   // ✅ Added delete option
            System.out.print("Enter choice: ");
            ch1 = sc.nextInt();

            switch (ch1) {
                case 1 -> {
                    System.out.println("Select Employee Type:");
                    System.out.println("1) Clerk  2) Developer  3) Tester  4) Manager");
                    int type = sc.nextInt();
                    Emp e = switch (type) {
                        case 1 -> new Clerk();
                        case 2 -> new Developer();
                        case 3 -> new Tester();
                        case 4 -> new Manager();
                        default -> null;
                    };
                    if (e != null) employees.add(e);
                    else System.out.println("Invalid choice!");
                }

                case 2 -> {
                    System.out.println("\n--- Employee List ---");
                    if (employees.isEmpty()) System.out.println("No employees found.");
                    else {
                        for (Emp emp : employees) {
                            emp.display();
                            System.out.println("------------------");
                        }
                    }
                }

                case 3 -> {
                    if (employees.isEmpty()) {
                        System.out.println("No employees available!");
                        break;
                    }
                    System.out.println("\n--- Raise Salary ---");
                    for (int i = 0; i < employees.size(); i++) {
                        System.out.println((i + 1) + ") " + employees.get(i).name + " (" + employees.get(i).desig + ")");
                    }
                    System.out.print("Select Employee No: ");
                    int idx = sc.nextInt() - 1;
                    if (idx >= 0 && idx < employees.size())
                        employees.get(idx).raisesalary();
                    else
                        System.out.println("Invalid Employee!");
                }

                case 4 -> {
                    if (employees.isEmpty()) {
                        System.out.println("No employees available!");
                        break;
                    }
                    System.out.println("\n--- Leaves Left ---");
                    for (Emp emp : employees) {
                        System.out.print(emp.name + " (" + emp.desig + "): ");
                        emp.leavesleft();
                    }
                }

                case 5 -> System.out.println("Exiting... Thank you!");

                case 6 -> {   // ✅ Delete option
                    if (employees.isEmpty()) {
                        System.out.println("No employees to delete!");
                        break;
                    }
                    System.out.println("\n--- Delete Employee ---");
                    for (int i = 0; i < employees.size(); i++) {
                        System.out.println((i + 1) + ") " + employees.get(i).name + " (" + employees.get(i).desig + ")");
                    }
                    System.out.print("Select Employee No to Delete: ");
                    int idx = sc.nextInt() - 1;
                    if (idx >= 0 && idx < employees.size()) {
                        System.out.println(employees.get(idx).name + " removed successfully!");
                        employees.remove(idx);
                    } else {
                        System.out.println("Invalid Employee!");
                    }
                }

                default -> {
                    if (ch1 != 5) System.out.println("Invalid Choice!");
                }
            }
        } while (ch1 != 5);
    }
}
