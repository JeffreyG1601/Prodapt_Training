package d12j1;
import java.util.Scanner;

class EmpDetails {
    protected int uid, age;
    protected String name, designation;
    protected int salary;

    public EmpDetails(String designation, int salary) {
        this.designation = designation;
        this.salary = salary;
        inputDetails();
    }

    protected void inputDetails() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter User ID:");
        uid = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Name:");
        name = sc.nextLine();
        System.out.println("Enter Age:");
        age = sc.nextInt();
        sc.nextLine();
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
        System.out.println("==========================");
        System.out.println("Enter the amount to be raised:");
        int x = sc.nextInt();
        salary += x;
        System.out.println("Increased Salary: " + salary);
        System.out.println("==========================");
    }
}

class Clerk extends EmpDetails {
    public Clerk() {
        super("Clerk", 10000);
    }
}

class Tester extends EmpDetails {
    public Tester() {
        super("Tester", 15000);
    }
}

class Manager extends EmpDetails {
    public Manager() {
        super("Manager", 20000);
    }
}

class Developer extends EmpDetails {
    public Developer() {
        super("Developer", 25000);
    }
}

public class d12c5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmpDetails[] employees = new EmpDetails[4]; // 0: Clerk, 1: Tester, 2: Manager, 3: Developer

        while (true) {
            System.out.println("=======================================");
            System.out.println("Select one of the following options:");
            System.out.println("1. Create \n2. Display \n3. Raise \n4. Exit");
            System.out.println("=======================================");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 4) {
                System.out.println("Exiting...");
                break;
            }

            System.out.println("Select the designation of the employee:");
            System.out.println("1. Clerk \n2. Tester \n3. Manager \n4. Developer");
            System.out.print("=======================================\nEnter your choice: ");
            int type = sc.nextInt();
            sc.nextLine();
            int index = type - 1;

            if (index < 0 || index >= 4) {
                System.out.println("Invalid designation choice.");
                continue;
            }

            switch (choice) {
                case 1:
                    switch (type) {
                        case 1 -> employees[index] = new Clerk();
                        case 2 -> employees[index] = new Tester();
                        case 3 -> employees[index] = new Manager();
                        case 4 -> employees[index] = new Developer();
                    }
                    break;

                case 2:
                    if (employees[index] != null)
                        employees[index].display();
                    else
                        System.out.println("No such employee created yet.");
                    break;

                case 3:
                    if (employees[index] != null)
                        employees[index].raise();
                    else
                        System.out.println("No such employee created yet.");
                    break;

                default:
                    System.out.println("Invalid menu choice.");
            }
        }
    }
}
