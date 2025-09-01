import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeService service = new EmployeeService();

        while (true) {
            System.out.println("\n===== Employee Menu =====");
            System.out.println("1) Create");
            System.out.println("2) Raise (Update Salary)");
            System.out.println("3) Delete");
            System.out.println("4) Display");
            System.out.println("5) Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            System.out.println("============================");

            if (choice == 5) {
                System.out.println("Exiting program...");
                sc.close();
                return;
            }

            // Select designation
            System.out.println("\nSelect Designation:");
            System.out.println("1) Clerk");
            System.out.println("2) Manager");
            System.out.println("3) Developer");
            System.out.println("4) Tester");
            System.out.print("Enter designation choice: ");
            int desigChoice = sc.nextInt();
            System.out.println("============================");

            String designation;
            switch (desigChoice) {
                case 1: designation = "Clerk"; break;
                case 2: designation = "Manager"; break;
                case 3: designation = "Developer"; break;
                case 4: designation = "Tester"; break;
                default:
                    System.out.println("Invalid designation choice.");
                    continue;
            }

            switch (choice) {
                case 1: // create
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter Name: ");
                    String name = sc.next();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    System.out.print("Enter Salary: ");
                    int salary = sc.nextInt();

                    Employee emp = new Employee(id, name, age, salary, designation);
                    service.createEmployee(emp);
                    break;

                case 2: // update
                    System.out.print("Enter ID to update: ");
                    int updId = sc.nextInt();
                    System.out.print("Enter new salary: ");
                    int newSalary = sc.nextInt();
                    service.updateSalary(updId, designation, newSalary);
                    break;

                case 3: // delete
                    System.out.print("Enter ID to delete: ");
                    int delId = sc.nextInt();
                    service.deleteEmployee(delId, designation);
                    break;

                case 4: // display
                    service.displayEmployees(designation);
                    break;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}
