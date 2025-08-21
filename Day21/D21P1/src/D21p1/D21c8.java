package D21p1;

import java.util.*;

class FoodItem {
    int id;
    String name;
    double price;

    FoodItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return id + ". " + name + " - Rs." + price;
    }
}

class Order {
    FoodItem item;
    int quantity;

    Order(FoodItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    double getTotal() {
        return item.price * quantity;
    }

    @Override
    public String toString() {
        return item.name + " x " + quantity + " = Rs." + getTotal();
    }
}

public class D21c8 {
    private static List<FoodItem> menu = new ArrayList<>();
    private static Deque<Order> orders = new ArrayDeque<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Predefined menu
        menu.add(new FoodItem(1, "Burger", 120));
        menu.add(new FoodItem(2, "Pizza", 250));
        menu.add(new FoodItem(3, "Pasta", 180));
        menu.add(new FoodItem(4, "French Fries", 90));
        menu.add(new FoodItem(5, "Coke", 50));

        while (true) {
            System.out.println("\n--- Restaurant System ---");
            System.out.println("1. View Menu");
            System.out.println("2. Place Order");
            System.out.println("3. View Current Orders");
            System.out.println("4. Generate Bill");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: viewMenu(); break;
                case 2: placeOrder(); break;
                case 3: viewOrders(); break;
                case 4: generateBill(); break;
                case 5: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // View menu
    private static void viewMenu() {
        System.out.println("\n--- Menu ---");
        for (FoodItem item : menu) {
            System.out.println(item);
        }
    }

    // Place order
    private static void placeOrder() {
        viewMenu();
        System.out.print("Enter Food ID to order: ");
        int id = sc.nextInt();
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        for (FoodItem item : menu) {
            if (item.id == id) {
                orders.addLast(new Order(item, qty)); // FIFO queue
                System.out.println("✅ Order placed: " + item.name + " x " + qty);
                return;
            }
        }
        System.out.println("Invalid Food ID!");
    }

    // View current orders
    private static void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders placed yet!");
            return;
        }
        System.out.println("\n--- Current Orders ---");
        for (Order o : orders) {
            System.out.println(o);
        }
    }

    // Generate bill
    private static void generateBill() {
        if (orders.isEmpty()) {
            System.out.println("No orders to bill!");
            return;
        }
        double total = 0;
        System.out.println("\n--- Bill ---");
        for (Order o : orders) {
            System.out.println(o);
            total += o.getTotal();
        }
        System.out.println("Total Amount = Rs." + total);
        orders.clear(); // After billing, clear orders
        System.out.println("✅ Orders cleared after billing.");
    }
}
