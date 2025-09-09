import java.util.Scanner;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainApp {
    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        try {
            // "JPA_PU" is the name of the persistence-unit in persistence.xml
            entityManagerFactory = Persistence.createEntityManagerFactory("JPA_PU");
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
                    case 5: 
                        entityManagerFactory.close();
                        System.exit(0);
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
                    case 1: CreateRecord.main(new String[]{designation}, entityManagerFactory); break;
                    case 2: DisplayRecord.main(new String[]{designation}, entityManagerFactory); break;
                    case 3: UpdateRecord.main(new String[]{designation}, entityManagerFactory); break;
                    case 4: DeleteRecord.main(new String[]{designation}, entityManagerFactory); break;
                    default: System.out.println("Invalid option");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    }
}