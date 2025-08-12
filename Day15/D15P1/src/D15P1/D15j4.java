package D15P1;

import java.util.InputMismatchException;
import java.util.Scanner;

abstract class EmpDetails {
    protected int uid, age;
    protected String name, designation;
    protected int salary;

    public EmpDetails(String designation, int salary) {
        this.designation = designation;
        this.salary = salary;
        inputDetails();
    }

    abstract void checktest();

    protected void inputDetails() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter User ID: ");
            uid = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Name: ");
            name = sc.nextLine();
            System.out.print("Enter Age: ");
            age = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type! Please enter valid numbers where required.");
            sc.nextLine(); // clear buffer
        }
    }

    public void display() {
        System.out.println("==========================");
        System.out.println("User ID: " + uid);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Designation: " + designation);
        System.out.println("Salary: " + salary);
        System.out.println("==========================");
    }

    public void raise() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter the amount to be raised: ");
            int x = sc.nextInt();
            if (x <= 0) {
                throw new IllegalArgumentException("Raise amount must be positive.");
            }
            salary += x;
            System.out.println("Increased Salary: " + salary);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a number.");
            sc.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

// Child classes
class Clerk extends EmpDetails {
    public Clerk() { super("Clerk", 10000); }
    @Override void checktest() {
        System.out.println("beep beep boop boop 1");
    }
}
class Tester extends EmpDetails {
    public Tester() { super("Tester", 15000); }
    @Override void checktest() {
        System.out.println("beep beep boop boop 2");
    }
}
class Manager extends EmpDetails {
    public Manager() { super("Manager", 20000); }
    @Override void checktest() {
        System.out.println("beep beep boop boop 3");
    }
}
class Developer extends EmpDetails {
    public Developer() { super("Developer", 25000); }
    @Override void checktest() {
        System.out.println("beep beep boop boop 4");
    }
}

public class D15j4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmpDetails[] employees = new EmpDetails[4]; // Clerk, Tester, Manager, Developer

        while (true) {
            try {
                System.out.println("=======================================");
                System.out.println("1. Create \n2. Display \n3. Raise \n4. Check\n5. Exit");
                System.out.println("=======================================");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 5) {
                    System.out.println("Exiting...");
                    break;
                }
                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid menu choice.");
                    continue;
                }

                System.out.println("1. Clerk \n2. Tester \n3. Manager \n4. Developer");
                System.out.print("Enter designation choice: ");
                int type = sc.nextInt();
                sc.nextLine();
                int index = type - 1;

                if (index < 0 || index >= 4) {
                    System.out.println("Invalid designation choice.");
                    continue;
                }

                switch (choice) {
                    case 1 -> employees[index] = switch (type) {
                        case 1 -> new Clerk();
                        case 2 -> new Tester();
                        case 3 -> new Manager();
                        case 4 -> new Developer();
                        default -> null;
                    };
                    case 2 -> showIfExists(employees[index], "display");
                    case 3 -> raiseIfExists(employees[index]);
                    case 4 -> checkIfExists(employees[index]);
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numbers only.");
                sc.nextLine(); // clear buffer
            }
        }
    }

    private static void showIfExists(EmpDetails emp, String action) {
        if (emp != null) emp.display();
        else System.out.println("No such employee created yet.");
    }

    private static void raiseIfExists(EmpDetails emp) {
        if (emp != null) emp.raise();
        else System.out.println("No such employee created yet.");
    }

    private static void checkIfExists(EmpDetails emp) {
        if (emp != null) emp.checktest();
        else System.out.println("No such employee created yet.");
    }
}
