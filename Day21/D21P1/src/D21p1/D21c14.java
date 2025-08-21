package D21p1;

import java.util.*;

class Customer {
    int id;
    String name;

    Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer ID: " + id + ", Name: " + name;
    }
}

public class D21c14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<Customer> bankQueue = new LinkedList<>();

        while (true) {
            System.out.println("\n=== Bank Queue System ===");
            System.out.println("1. Add Customer");
            System.out.println("2. Serve Customer");
            System.out.println("3. View Queue");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Customer ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Customer Name: ");
                    String name = sc.nextLine();
                    bankQueue.add(new Customer(id, name));
                    System.out.println("✅ Customer added to queue.");
                    break;

                case 2:
                    if (bankQueue.isEmpty()) {
                        System.out.println("⚠️ No customers in queue.");
                    } else {
                        Customer served = bankQueue.poll();
                        System.out.println("🛎️ Serving " + served);
                    }
                    break;

                case 3:
                    if (bankQueue.isEmpty()) {
                        System.out.println("⚠️ Queue is empty.");
                    } else {
                        System.out.println("📋 Current Queue:");
                        for (Customer c : bankQueue) {
                            System.out.println(c);
                        }
                    }
                    break;

                case 4:
                    System.out.println("🏦 Exiting Bank Queue System. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }
}
