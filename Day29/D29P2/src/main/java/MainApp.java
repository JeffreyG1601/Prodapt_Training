import java.util.Scanner;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n=== Main Menu ===");
                System.out.println("1. Clerk");
                System.out.println("2. Developer");
                System.out.println("3. Manager");
                System.out.println("4. Tester");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                String designation = null;
                switch (choice) {
                    case 1: designation = "Clerk"; break;
                    case 2: designation = "Developer"; break;
                    case 3: designation = "Manager"; break;
                    case 4: designation = "Tester"; break;
                    case 5: System.exit(0);
                    default: System.out.println("Invalid choice"); continue;
                }

                System.out.println("\n--- " + designation + " Menu ---");
                System.out.println("1. Create");
                System.out.println("2. Display");
                System.out.println("3. Update");
                System.out.println("4. Delete");
                System.out.print("Enter your choice: ");
                int op = sc.nextInt();

                switch (op) {
                    case 1: CreateRecord.main(new String[]{designation}, sessionFactory); break;
                    case 2: DisplayRecord.main(new String[]{designation}, sessionFactory); break;
                    case 3: UpdateRecord.main(new String[]{designation}, sessionFactory); break;
                    case 4: DeleteRecord.main(new String[]{designation}, sessionFactory); break;
                    default: System.out.println("Invalid option");
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
