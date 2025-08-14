import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Employee Menu =====");
            System.out.println("1) Create");
            System.out.println("2) Raise");
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

            // Select designation first
            System.out.println("\nSelect Designation:");
            System.out.println("1) Clerk");
            System.out.println("2) Manager");
            System.out.println("3) Developer");
            System.out.println("4) Tester");
            System.out.print("Enter designation choice: ");
            int desigChoice = sc.nextInt();
            System.out.println("============================");
            String designation = "";

            switch (desigChoice) {
                case 1: designation = "Clerk"; break;
                case 2: designation = "Manager"; break;
                case 3: designation = "Developer"; break;
                case 4: designation = "Tester"; break;
                default:
                    System.out.println("Invalid designation choice.");
                    continue;
            }

            // Pass designation to corresponding operation
            switch (choice) {
                case 1:
                    CreateRecord.main(new String[]{designation});
                    break;
                case 2:
                    UpdateRecord.main(new String[]{designation});
                    break;
                case 3:
                    DeleteRecord.main(new String[]{designation});
                    break;
                case 4:
                    DisplayRecord.main(new String[]{designation});
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}
